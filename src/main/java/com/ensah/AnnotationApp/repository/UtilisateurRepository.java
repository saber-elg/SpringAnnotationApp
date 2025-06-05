package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.Annotateur;
import com.ensah.AnnotationApp.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByLogin(String login); //Ce retour peut exister, ou non
    @Query("SELECT a FROM Annotateur a WHERE a.utilisateur.actif = true")
    List<Annotateur> findAnnotateursActifs();
}
