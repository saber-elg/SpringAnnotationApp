package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.Annotateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface AnnotateurRepository extends JpaRepository<Annotateur, Integer> {
    // Trouver un annotateur à partir de l'ID utilisateur
    Optional<Annotateur> findByUtilisateurId(Integer id);
    @Query("SELECT DISTINCT t.annotateur FROM Tache t WHERE t.dataset.id = :datasetId AND t.annotateur IS NOT NULL")
    List<Annotateur> findByDatasetId(@Param("datasetId") Integer datasetId);
    List<Annotateur> findByUtilisateurActifTrue();
}
