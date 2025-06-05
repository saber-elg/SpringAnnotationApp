package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer> {
    List<Tache> findByAnnotateurUtilisateurId(Integer utilisateurId);
    List<Tache> findByDatasetIdAndAnnotateurUtilisateurId(Integer datasetId, Integer utilisateurId);
    boolean existsByAnnotateurUtilisateurIdAndAnnotateurUtilisateurActifTrue(Integer utilisateurId);
}
