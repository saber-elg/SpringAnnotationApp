package com.ensah.AnnotationApp.Controller;

import com.ensah.AnnotationApp.entity.*;
import com.ensah.AnnotationApp.repository.*;
import com.ensah.AnnotationApp.service.AnnotateurService;
import com.ensah.AnnotationApp.service.IDatasetService;
import com.ensah.AnnotationApp.service.UtilisateurService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import static org.apache.juli.JsonFormatter.JSONFilter.escape;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AnnotateurService annotateurService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AnnotateurRepository annotateurRepository ;
    @Autowired
    private DatasetRepository datasetRepository;
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IDatasetService datasetService;
    @Autowired
    private CoupleTexteRepository coupleTexteRepository;
    @Autowired
    private AnnotationRepository annotationRepository;

    @GetMapping("/home")
    public String homeAdmin(Authentication authentication, Model model) {
        Utilisateur utilisateur = utilisateurRepository.findByLogin(authentication.getName())//renvoie le nom d'utilisateur (username) utilisé lors de l’authentification. ce username est celui qu'on a dans le champ name="username" du formulaire de login
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        int nbAnnotateurs = annotateurRepository.findByUtilisateurActifTrue().size();

        List<Dataset> allDatasets = datasetService.getAllDatasets();
        int nbdatasets = allDatasets.size();

        // Compter les datasets terminés et non terminés
        int datasetsCompleted = 0;
        int datasetsNotCompleted = 0;

        for (Dataset dataset : allDatasets) {
            if (isDatasetCompleted(dataset)) {
                datasetsCompleted++;
            } else {
                datasetsNotCompleted++;
            }
        }

        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom());
        model.addAttribute("nbannotateurs", nbAnnotateurs);
        model.addAttribute("nbdatasets", nbdatasets);
        model.addAttribute("datasetsCompleted", datasetsCompleted);
        model.addAttribute("datasetsNotCompleted", datasetsNotCompleted);
        return "/admin/homeAdmin";
    }

    private boolean isDatasetCompleted(Dataset dataset) {
        if (dataset == null) return false;

        long total = coupleTexteRepository.countByDataset(dataset);
        if (total == 0) return false; // Can't be completed if there are no couples

        long annotes = annotationRepository.countByDataset(dataset);
        double avancement = (annotes * 100.0) / total;

        return avancement >= 100.0;
    }

    @GetMapping("/creer-dataset")
    public String createDatasetForm() {
        return "/admin/creer-dataset"; // Retourne la page HTML du formulaire de création de dataset
    }

    @PostMapping("/creer-dataset")
    //les champs de formulaire sont toujours des paramètres nommés , Spring les mappe automatiquement
    public String createDataset(@RequestParam String nomDataset,
                                @RequestParam String classes,
                                @RequestParam(required = false) String description,
                                @RequestParam("file") MultipartFile file ,
                                org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        // Appelle le service pour créer le dataset
        datasetService.createDataset(nomDataset, description, classes, file);
        redirectAttributes.addFlashAttribute("successMessage", "Dataset créé avec succès !");
        // Redirige vers la page des datasets ou la page d'accueil administrateur
        return "redirect:/admin/home";
    }

    @GetMapping("/liste-datasets")
    public String listDatasets(Model model) {
        List<Dataset> datasets = datasetService.getAllDatasets();

        Map<Dataset, Double> datasetAvancements = new LinkedHashMap<>();
        for (Dataset dataset : datasets) {
            long total = coupleTexteRepository.countByDataset(dataset);
            long annotes = annotationRepository.countByDataset(dataset);
            double avancement = (total == 0) ? 0 : (annotes * 100.0) / total; //avanc = nombre de couples annote * 100 / nbr totale de couples
            datasetAvancements.put(dataset, avancement);
        }
        model.addAttribute("datasetAvancements", datasetAvancements);
        return "/admin/liste-datasets";
    }

    @GetMapping("/details-dataset/{id}")
    public String afficherDetailsDataset(
            @PathVariable Integer id,
            @RequestParam(defaultValue = "0") int page,  //pas de premier execution mais lorsque on veux passer a une autre pages
            Model model) {
        Dataset dataset = datasetService.getDatasetById(id);
        PageRequest pageRequest = PageRequest.of(page, 100);
        Page<CoupleTexte> couplesPage = coupleTexteRepository.findByDataset(dataset, pageRequest); // 100 au moin de couples seulement
        long totalCouples = coupleTexteRepository.countByDataset(dataset);
        long annotes = annotationRepository.countByDataset(dataset);
        double avancement = (totalCouples == 0) ? 0 : (annotes * 100.0) / totalCouples;
        model.addAttribute("dataset", dataset);
        model.addAttribute("couplesPage", couplesPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("annotateurs", annotateurService.getAnnotateursByDataset(id));
        model.addAttribute("avancement", avancement);
        Map<Integer, Map<Integer, Double>> avancementsParAnnotateur = new HashMap<>();
        List<Annotateur> annotateurs = annotateurService.getAnnotateursByDataset(id);
        for (Annotateur annotateur : annotateurs) {
            Map<Integer, Double> avancements = new HashMap<>();
            List<Tache> taches = tacheRepository.findByAnnotateurUtilisateurId(annotateur.getUtilisateur().getId())
                    .stream()
                    .filter(t -> t.getDataset().getId().equals(id)) // filtre : tache liée au dataset
                    .toList();
            for (Tache tache : taches) {
                int total = tache.getTaille();
                long nbAnn = tache.getCouples().stream()
                        .filter(c -> annotationRepository.existsByCouple(c))
                        .count();
                double av = total == 0 ? 0 : (100.0 * nbAnn)  / total ;
                avancements.put(tache.getId(), av);
            }
            avancementsParAnnotateur.put(annotateur.getUtilisateur().getId(), avancements);
        }
        model.addAttribute("avancementsParAnnotateur", avancementsParAnnotateur);
        return "admin/details-dataset";
    }

    @PostMapping("/supprimer-annotateur-dataset/{datasetId}/{annotateurId}")
    public String supprimerAnnotateurDataset(
            @PathVariable Integer datasetId,
            @PathVariable Integer annotateurId,
            RedirectAttributes redirectAttributes) {
        List<Tache> taches = tacheRepository.findByDatasetIdAndAnnotateurUtilisateurId(datasetId, annotateurId);
        Dataset dataset = datasetRepository.findById(datasetId)
                .orElseThrow(() -> new RuntimeException("Dataset non trouvé"));
        List<Annotateur> autresAnnotateurs = annotateurRepository.findAll()
                .stream()
                .filter(a -> a.getUtilisateur() != null && a.getUtilisateur().isActif() && !a.getUtilisateur().getId().equals(annotateurId))
                .collect(Collectors.toList());
        if (autresAnnotateurs.isEmpty()) {
            redirectAttributes.addFlashAttribute("erreur", "Aucun autre annotateur disponible pour réaffectation.");
            return "redirect:/admin/details-dataset/" + datasetId;
        }
        int nbTachesTraitees = 0;
        int nbReaffectees = 0;
        int nbDesaffectees = 0;
        for (Tache tache : taches) {
            nbTachesTraitees++;
            int total = tache.getTaille();
            long nbAnn = tache.getCouples().stream()
                    .filter(c -> annotationRepository.existsByCouple(c))
                    .count();
            double avancement = total == 0 ? 0.0 : (100.0 * nbAnn / total);
            if (avancement == 100.0) {
                tache.setAnnotateur(null);
                tacheRepository.save(tache);
                nbDesaffectees++;
            } else if (avancement == 0.0) {
                Annotateur nouveauAnnotateur = choisirAnnotateurAleatoire(autresAnnotateurs);
                tache.setAnnotateur(nouveauAnnotateur);
                tacheRepository.save(tache);
                nbReaffectees++;
            } else {
                tache.setAnnotateur(null);
                tacheRepository.save(tache);
                nbDesaffectees++;
                Tache nouvelleTache = new Tache();
                nouvelleTache.setDataset(dataset);
                nouvelleTache.setDelaiLimite(tache.getDelaiLimite());
                Annotateur nouveauAnnotateur = choisirAnnotateurAleatoire(autresAnnotateurs);
                nouvelleTache.setAnnotateur(nouveauAnnotateur);

                List<CoupleTexte> nonAnn = tache.getCouples().stream()
                        .filter(c -> !annotationRepository.existsByCouple(c))
                        .collect(Collectors.toList());

                nouvelleTache.setCouples(new ArrayList<>(nonAnn));
                tacheRepository.save(nouvelleTache);

                tache.getCouples().removeAll(nonAnn);
                tacheRepository.save(tache);
                nbReaffectees++;
            }
        }
        redirectAttributes.addFlashAttribute("success",
                String.format("Annotateur supprimé du dataset. %d tâche(s) traitée(s), %d réaffectée(s), %d désaffectée(s).",
                        nbTachesTraitees, nbReaffectees, nbDesaffectees));

        return "redirect:/admin/details-dataset/" + datasetId;
    }

    private Annotateur choisirAnnotateurAleatoire(List<Annotateur> annotateurs) {
        Random rand = new Random();
        return annotateurs.get(rand.nextInt(annotateurs.size()));
    }

    @GetMapping("/ajouter-annotateur-dataset/{datasetId}")
    public String afficherFormulaireAffectationAnnotateurs(@PathVariable Integer datasetId, Model model ,   RedirectAttributes redirectAttributes) {
        Optional<Dataset> datasetOpt = datasetRepository.findById(datasetId);

        if (datasetOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Dataset introuvable.");
            return "redirect:/admin/liste-datasets";
        }
        Dataset dataset = datasetOpt.get();
        long totalCouples = coupleTexteRepository.countByDataset(dataset);
        long annotes = annotationRepository.countByDataset(dataset);
        double avancement = (totalCouples == 0) ? 0 : (annotes * 100.0) / totalCouples;
        if (avancement == 100.0) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Impossible d’affecter des annotateurs : le dataset est déjà complété à 100%.");
            return "redirect:/admin/liste-datasets";
        }
        List<Annotateur> annotateursAffectes = annotateurRepository.findByDatasetId(datasetId);

        if (!annotateursAffectes.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Les annotateurs ont déjà été affectés à ce dataset. Cette opération ne peut être faite qu'une seule fois.");
            return "redirect:/admin/liste-datasets";
        }

        List<Annotateur> tousAnnotateurs = annotateurRepository.findByUtilisateurActifTrue();

        model.addAttribute("datasetId", datasetId);
        model.addAttribute("annotateurs", tousAnnotateurs);
        return "/admin/affecter-annotateurs";
    }

    @PostMapping("/affecter-annotateurs")
    public String affecterAnnotateurs(
            @RequestParam("datasetId") Integer datasetId,
            @RequestParam("annotateurIds") List<Integer> annotateurIds,
            RedirectAttributes redirectAttributes) {
        datasetService.affecterAnnotateursAuDataset(datasetId, annotateurIds);
        redirectAttributes.addFlashAttribute("successMessage", "Annotateurs affectés avec succès !");
        return "redirect:/admin/liste-datasets";
    }

    @GetMapping("/exporter-csv/{datasetId}")
    public void exporterCsv(@PathVariable("datasetId") Integer datasetId, HttpServletResponse response) throws IOException {
        Dataset dataset = datasetService.getDatasetById(datasetId);
        List<CoupleTexte> couples = coupleTexteRepository.findByDataset(dataset);
        long total = coupleTexteRepository.countByDataset(dataset);
        long annotes = annotationRepository.countByDataset(dataset);
        double avancement = (total == 0) ? 0 : (annotes * 100.0) / total;
        if (avancement != 100) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "L'avancement du dataset doit être de 100% pour exporter.");
            return;
        }
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"dataset_" + datasetId + "_annotations.csv\"");
        // Créer un writer pour écrire le contenu CSV
        PrintWriter writer = response.getWriter();
        writer.println("Texte1;Texte2;Annotation;Annotateur");
        // Boucler sur les couples et écrire leurs données dans le fichier CSV
        for (CoupleTexte couple : couples) {
            // Récupérer l'annotation associée à ce couple de texte
            Annotation annotation = annotationRepository.findByCouple(couple);
            // Vérifier si l'annotation existe et obtenir les informations nécessaires
            String annotationText = annotation != null ? annotation.getClasseChoisie().getIntituleClasse() : "Non annoté";
            String nom = "N/A";
            String prenom = "N/A";
            if (annotation != null && annotation.getAnnotateur() != null && annotation.getAnnotateur().getUtilisateur() != null) {
                nom = annotation.getAnnotateur().getUtilisateur().getNom();
                prenom = annotation.getAnnotateur().getUtilisateur().getPrenom();
            }
            writer.println(couple.getTexte1() + ";" + couple.getTexte2() + ";" + annotationText + ";" +  escape(nom + " " + prenom));
        }
        writer.flush();
    }

    @GetMapping("/gerer-annotateurs")
    public String afficherListeAnnotateurs(Model model) {
        List<Annotateur> annotateurs = utilisateurRepository.findAnnotateursActifs();
        model.addAttribute("annotateurs", annotateurs);
        return "/admin/gerer_annotateurs";
    }

    @GetMapping("/ajouter-annotateur")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "admin/ajouter_annotateur";
    }

    @PostMapping("/ajouter-annotateur")
    public String ajouterAnnotateur(@ModelAttribute Utilisateur utilisateur, RedirectAttributes redirectAttributes) {
        // Générer un mot de passe
        String motDePasseGenere = generateRandomPassword(8);
        // Bcrypter le mot de passe
        String motDePasseBcrypte = passwordEncoder.encode(motDePasseGenere);
        // Définir le mot de passe chiffré dans l'utilisateur
        utilisateur.setPassword(motDePasseBcrypte);
        // Attribuer le rôle ANNOTATEUR
        Role roleAnnotateur = roleRepository.findByNomRole(Role.NomRole.USER_ROLE);
        utilisateur.setRole(roleAnnotateur);
        // Sauvegarde
        utilisateurService.ajouterUtilisateur(utilisateur);
        // Création annotateur lié à l'utilisateur
        Annotateur annotateur = new Annotateur();
        annotateur.setUtilisateurId(utilisateur.getId());
        annotateurRepository.save(annotateur);
        // Ajouter le mot de passe en clair dans les attributs flash
        redirectAttributes.addFlashAttribute("success", "Annotateur ajouté avec succès !");
        redirectAttributes.addFlashAttribute("motDePasseGenere", motDePasseGenere);
        return "redirect:/admin/gerer-annotateurs";
    }

    // Fonction utilitaire pour générer un mot de passe aléatoire
    private String generateRandomPassword(int longueur) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder motDePasse = new StringBuilder();
        for (int i = 0; i < longueur; i++) {
            int index = (int) (Math.random() * caracteres.length());
            motDePasse.append(caracteres.charAt(index));
        }
        return motDePasse.toString();
    }

    @GetMapping("/modifier-annotateur/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Annotateur annotateur = annotateurService.getAnnotateurById(id);
        model.addAttribute("annotateur", annotateur);
        return "/admin/modifier-annotateur";
    }

    @PostMapping("/modifier-annotateur/{id}")
    public String updateAnnotateur(@PathVariable Integer id,
                                   @RequestParam String nom,
                                   @RequestParam String prenom,
                                   @RequestParam String login,
                                   RedirectAttributes redirectAttributes) {
        annotateurService.updateAnnotateur(id, nom, prenom , login);
        redirectAttributes.addFlashAttribute("successMessage", "Annotateur modifié avec succès !");
        return "redirect:/admin/gerer-annotateurs";
    }

    @GetMapping("/supprimer-annotateur/{id}")
    public String supprimerAnnotateurLogique(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findById(id);

        if (utilisateurOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Annotateur introuvable.");
            return "redirect:/admin/gerer-annotateurs";
        }

        Utilisateur utilisateur = utilisateurOpt.get();

        List<Tache> taches = tacheRepository.findByAnnotateurUtilisateurId(utilisateur.getId());
        List<Annotateur> autresAnnotateurs = annotateurRepository.findAll().stream()
                .filter(a -> a.getUtilisateur() != null && a.getUtilisateur().isActif() && !a.getUtilisateur().getId().equals(id))
                .collect(Collectors.toList());

        int nbTachesTraitees = 0;
        int nbReaffectees = 0;
        int nbDesaffectees = 0;

        for (Tache tache : taches) {
            nbTachesTraitees++;
            int total = tache.getTaille();
            long nbAnn = tache.getCouples().stream()
                    .filter(c -> annotationRepository.existsByCouple(c))
                    .count();
            double avancement = total == 0 ? 0.0 : (100.0 * nbAnn / total);

            Dataset dataset = tache.getDataset();

            if (avancement == 100.0) {
                // désaffectation simple
                tache.setAnnotateur(null);
                tacheRepository.save(tache);
                nbDesaffectees++;
            } else if (avancement == 0.0) {
                // réaffectation directe
                Annotateur nouveauAnnotateur = choisirAnnotateurAleatoire(autresAnnotateurs);
                tache.setAnnotateur(nouveauAnnotateur);
                tacheRepository.save(tache);
                nbReaffectees++;
            } else {
                // réaffectation partielle : désaffecter les annotés et créer nouvelle tâche pour les non annotés
                tache.setAnnotateur(null);
                tacheRepository.save(tache);
                nbDesaffectees++;

                Tache nouvelleTache = new Tache();
                nouvelleTache.setDataset(dataset);
                nouvelleTache.setDelaiLimite(tache.getDelaiLimite());
                Annotateur nouveauAnnotateur = choisirAnnotateurAleatoire(autresAnnotateurs);
                nouvelleTache.setAnnotateur(nouveauAnnotateur);

                List<CoupleTexte> nonAnn = tache.getCouples().stream()
                        .filter(c -> !annotationRepository.existsByCouple(c))
                        .collect(Collectors.toList());

                nouvelleTache.setCouples(new ArrayList<>(nonAnn));
                tache.getCouples().removeAll(nonAnn);

                tacheRepository.save(tache);
                tacheRepository.save(nouvelleTache);
                nbReaffectees++;
            }
        }

        utilisateur.setActif(false);
        utilisateurRepository.save(utilisateur);

        redirectAttributes.addFlashAttribute("success",
                String.format("Annotateur supprimé. %d tâche(s) traitée(s), %d réaffectée(s), %d désaffectée(s).",
                        nbTachesTraitees, nbReaffectees, nbDesaffectees));

        return "redirect:/admin/gerer-annotateurs";
    }
}

