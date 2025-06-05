package com.ensah.AnnotationApp.service;
import com.ensah.AnnotationApp.entity.*;
import com.ensah.AnnotationApp.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class DatasetService implements IDatasetService{

    private static final Logger logger = LoggerFactory.getLogger(DatasetService.class);


    @Autowired
    private DatasetRepository datasetRepository;
    @Autowired
    private ClassePossibleRepository classePossibleRepository;
    @Autowired
    private CoupleTexteRepository coupleTexteRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private AnnotateurRepository annotateurRepository;
    @Autowired
    private TacheRepository tacheRepository;
    //on a pas que save de dataset , on a d'autre traitement ,si pourquoi ici dans service et pas repository
    public Dataset createDataset(String nomDataset, String description, String classes, MultipartFile file) {

        Dataset dataset = new Dataset();
        dataset.setNomDataset(nomDataset);
        dataset.setDescription(description);
        datasetRepository.save(dataset);

        String[] classList = classes.split(";");
        for (String className : classList) {
            ClassePossible classe = new ClassePossible();
            classe.setIntituleClasse(className.trim());  //setter de @Data
            classe.setDataset(dataset);   //setter de @Data
            classePossibleRepository.save(classe);
        }
        // Traiter le fichier pour extraire les couples de textes et les enregistrer
        extractAndSaveCouplesFromFile(file, dataset);
        // Retourner l'objet Dataset créé
        return dataset;
    }

    // Méthode pour extraire les couples de textes du fichier CSV
    private void extractAndSaveCouplesFromFile(MultipartFile file, Dataset dataset) {
        try {
            // Charger et parser le fichier CSV
            Path path = Path.of("uploads", file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);//copie le fichier dans le dosier uploads
            List<CSVRecord> records = CSVFormat.DEFAULT
                    .withDelimiter(';') // Utilisation du point-virgule comme séparateur
                    .withFirstRecordAsHeader()
                    .parse(Files.newBufferedReader(path))
                    .getRecords();
            // Pour chaque enregistrement (ligne) dans le fichier CSV, créer un CoupleTexte
            for (CSVRecord record : records) {
                String texte1 = record.get("texte1");  // Assurez-vous que le fichier CSV a une colonne "texte1"
                String texte2 = record.get("texte2");  // Et une colonne "texte2"
                // Créer un CoupleTexte
                CoupleTexte coupleTexte = new CoupleTexte();
                coupleTexte.setTexte1(texte1);
                coupleTexte.setTexte2(texte2);
                coupleTexte.setDataset(dataset);
                // Sauvegarder le CoupleTexte dans la base de données
                coupleTexteRepository.save(coupleTexte);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Dataset> getAllDatasets() {
        return datasetRepository.findAll();
    }

    public void affecterAnnotateursAuDataset(Integer datasetId, List<Integer> annotateurIds) {
        Dataset dataset = datasetRepository.findById(datasetId)
                .orElseThrow(() -> new RuntimeException("Dataset introuvable avec l'ID : " + datasetId));
        List<Utilisateur> utilisateurs = utilisateurRepository.findAllById(annotateurIds);
        List<Annotateur> annotateurs = new ArrayList<>();
        for (Utilisateur utilisateur : utilisateurs) {
            Annotateur annotateur = annotateurRepository.findById(utilisateur.getId())
                    .orElseThrow(() -> new RuntimeException("Annotateur introuvable pour l'utilisateur ID : " + utilisateur.getId()));
            annotateurs.add(annotateur);
        }
        List<CoupleTexte> couples = coupleTexteRepository.findByDataset(dataset);
        Collections.shuffle(couples);
        int nombreAnnotateurs = annotateurs.size();
        int nombreCouples = couples.size();
        int couplesParAnnotateur = nombreCouples / nombreAnnotateurs;
        int reste = nombreCouples % nombreAnnotateurs;
        int index = 0;
        for (Annotateur annotateur : annotateurs) {
            Tache tache = new Tache();
            tache.setAnnotateur(annotateur);
            tache.setDataset(dataset);
            LocalDate dateDansUnMois = LocalDate.now().plusMonths(1);
            Date dateLimite = Date.from(dateDansUnMois.atStartOfDay(ZoneId.systemDefault()).toInstant());
            tache.setDelaiLimite(dateLimite);
            // Initialisation de la liste des couples
            List<CoupleTexte> couplesPourTache = new ArrayList<>();
            int nombreCouplesPourCetAnnotateur = couplesParAnnotateur + (reste > 0 ? 1 : 0);
            reste = Math.max(0, reste - 1);
            for (int i = 0; i < nombreCouplesPourCetAnnotateur && index < couples.size(); i++) {
                couplesPourTache.add(couples.get(index++));
            }
            // Affecter la liste des couples à la tâche
            tache.setCouples(couplesPourTache);
            // Enregistrer la tâche (la relation sera persistée via @ManyToMany)
            tacheRepository.save(tache);
        }
    }

    public Dataset getDatasetById(Integer id) {
        Optional<Dataset> optionalDataset = datasetRepository.findById(id);
        if (optionalDataset.isPresent()) {
            return optionalDataset.get();
        } else {
            throw new RuntimeException("Dataset introuvable avec l'ID : " + id);
        }
    }
}
