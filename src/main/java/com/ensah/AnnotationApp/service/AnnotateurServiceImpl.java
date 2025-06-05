package com.ensah.AnnotationApp.service;
import com.ensah.AnnotationApp.entity.Annotateur;
import com.ensah.AnnotationApp.repository.AnnotateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnnotateurServiceImpl implements AnnotateurService {

    private static final Logger logger = LoggerFactory.getLogger(AnnotateurServiceImpl.class);

    @Autowired
    private AnnotateurRepository annotateurRepository;

    @Override
    public Annotateur getAnnotateurById(Integer id) {
        return annotateurRepository.findByUtilisateurId(id)
                .orElseThrow(() -> new RuntimeException("Annotateur non trouvé"));
    }
    @Override
    public void updateAnnotateur(Integer id, String nom, String prenom , String login) {
        Annotateur annotateur = annotateurRepository.findByUtilisateurId(id)
                .orElseThrow(() -> new RuntimeException("Annotateur non trouvé"));
        annotateur.getUtilisateur().setNom(nom);
        annotateur.getUtilisateur().setPrenom(prenom);
        annotateur.getUtilisateur().setLogin(login);
        annotateurRepository.save(annotateur);
    }

    public List<Annotateur> getAnnotateursByDataset(Integer datasetId) {
        return annotateurRepository.findByDatasetId(datasetId)
                .stream()
                .filter(a -> a != null && a.getUtilisateur() != null)
                .collect(Collectors.toList());
    }
}
