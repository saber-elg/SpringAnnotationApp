package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.Annotateur;
import com.ensah.AnnotationApp.entity.Annotation;
import com.ensah.AnnotationApp.entity.CoupleTexte;
import com.ensah.AnnotationApp.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AnnotationRepository extends JpaRepository<Annotation, Integer> {
    boolean existsByAnnotateurAndCouple(Annotateur annotateur, CoupleTexte couple);
    Optional<Annotation> findByAnnotateurAndCouple(Annotateur annotateur, CoupleTexte couple);
    // Nombre de couples annotés pour un dataset donné
    @Query("SELECT COUNT(a) FROM Annotation a WHERE a.couple.dataset = :dataset") //requête JPQL
    long countByDataset(@Param("dataset") Dataset dataset); //:dataset dans la requête est lié au paramètre Java via @Param("dataset")
    boolean existsByCouple(CoupleTexte couple);
    Annotation findByCouple(CoupleTexte couple); // Trouver une annotation par couple de texte
}

