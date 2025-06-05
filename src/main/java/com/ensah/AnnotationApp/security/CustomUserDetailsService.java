package com.ensah.AnnotationApp.security;
import com.ensah.AnnotationApp.entity.Utilisateur;
import com.ensah.AnnotationApp.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
//Ce service va charger l'utilisateur depuis la table Utilisateur et vérifier son Role
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //Si Optional est vide (i.e., si aucun utilisateur n’a été trouvé dans la base), alors lance cette exception
        Utilisateur utilisateur = utilisateurRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));//expression fonctionnelle utilisée avec l’API Optional
        if (!utilisateur.isActif()) {
            throw new DisabledException("Compte désactivé");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(utilisateur.getLogin())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRole().getNomRole().name().replace("_ROLE", ""))
                .build();
    }
}
