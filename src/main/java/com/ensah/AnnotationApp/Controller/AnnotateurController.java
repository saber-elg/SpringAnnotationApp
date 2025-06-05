package com.ensah.AnnotationApp.Controller;
import com.ensah.AnnotationApp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ensah.AnnotationApp.repository.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/annotateur")
public class AnnotateurController {
    @Autowired
    private TacheRepository tacheRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private CoupleTexteRepository coupleTexteRepo;
    @Autowired
    private ClassePossibleRepository classeRepo;
    @Autowired
    private AnnotationRepository annotationRepo;
    @Autowired
    private AnnotateurRepository annotateurRepo;

    @GetMapping("/home")
    public String homeAnnotateur(Authentication authentication, Model model) {
        Utilisateur utilisateur = utilisateurRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        model.addAttribute("nom", utilisateur.getNom());
        model.addAttribute("prenom", utilisateur.getPrenom());
        List<Tache> taches = tacheRepository.findByAnnotateurUtilisateurId(utilisateur.getId());
        Map<Integer, Double> avancements = new HashMap<>();
        for (Tache tache : taches) {
            int total = tache.getTaille();
            long nbAnn = tache.getCouples().stream()
                    .filter(c -> annotationRepo.existsByCouple(c))
                    .count();
            System.out.println("Tâche ID: " + tache.getId());
            System.out.println(" - Total couples: " + total);
            System.out.println(" - Annotés: " + nbAnn);
            double avancement = total == 0 ? 0.0 : (100.0 * nbAnn / total);
            System.out.printf(" - Avancement calculé: %.2f%%\n", avancement);
            avancements.put(tache.getId(), avancement);
        }
        model.addAttribute("avancements", avancements);
        model.addAttribute("taches", taches);
        return "annotateur/homeAnnotateur";
    }

    @GetMapping("/travailler/{tacheId}")
    public String travailler(@PathVariable Integer tacheId,
                             @RequestParam(required = false) Integer index,
                             Model model) {
        Tache tache = tacheRepository.findById(tacheId)
                .orElseThrow(() -> new IllegalArgumentException("Tâche non trouvée"));
        List<CoupleTexte> couples = tache.getCouples();
        if (index == null) {
            // Chercher le premier couple non annoté pour cet annotateur
            Annotateur annotateur = tache.getAnnotateur();
            for (int i = 0; i < couples.size(); i++) {
                if (!annotationRepo.existsByAnnotateurAndCouple(annotateur, couples.get(i))) {
                    index = i;
                    break;
                }
            }
            if (index == null) index = 0; // si tous sont annotés
        }
        // Sécurité
        if (index < 0 || index >= couples.size()) {
            return "redirect:/annotateur/home";
        }
        model.addAttribute("couple", couples.get(index));
        model.addAttribute("index", index);
        model.addAttribute("total", couples.size());
        model.addAttribute("dataset", tache.getDataset());
        model.addAttribute("classes", classeRepo.findByDataset(tache.getDataset()));
        model.addAttribute("annotateur", tache.getAnnotateur());
        model.addAttribute("tacheId", tacheId);
        // Pré-remplir l'annotation si elle existe
        Optional<Annotation> annotation = annotationRepo.findByAnnotateurAndCouple(tache.getAnnotateur(), couples.get(index));
        annotation.ifPresent(a -> model.addAttribute("selectedClasseId", a.getClasseChoisie().getId()));
        return "annotateur/annotateur-travailler";
    }

    @PostMapping("/travailler/{annotateurId}/valider")
    public String validerAnnotation(@PathVariable Integer annotateurId,
                                    @RequestParam int coupleId,
                                    @RequestParam int classeId,
                                    @RequestParam int index,
                                    @RequestParam int tacheId) {

        Annotateur annotateur = annotateurRepo.findById(annotateurId)
                .orElseThrow(() -> new IllegalArgumentException("Annotateur non trouvé"));
        CoupleTexte coupleTexte = coupleTexteRepo.findById(coupleId)
                .orElseThrow(() -> new IllegalArgumentException("Couple non trouvé"));
        ClassePossible classePossible = classeRepo.findById(classeId)
                .orElseThrow(() -> new IllegalArgumentException("Classe non trouvée"));
        // Vérifie si une annotation existe déjà
        Optional<Annotation> existingAnnotationOpt = annotationRepo.findByAnnotateurAndCouple(annotateur, coupleTexte);

        Annotation annotation = existingAnnotationOpt.orElse(new Annotation());
        annotation.setAnnotateur(annotateur);
        annotation.setCouple(coupleTexte);
        annotation.setClasseChoisie(classePossible);
        annotationRepo.save(annotation);
        return "redirect:/annotateur/travailler/" + tacheId + "?index=" + (index + 1);
    }
}