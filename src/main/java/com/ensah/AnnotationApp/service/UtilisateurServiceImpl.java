package com.ensah.AnnotationApp.service;

import com.ensah.AnnotationApp.entity.Utilisateur;
import com.ensah.AnnotationApp.repository.UtilisateurRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }
}
