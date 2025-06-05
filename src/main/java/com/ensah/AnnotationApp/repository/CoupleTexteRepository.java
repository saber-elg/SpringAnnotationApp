package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.CoupleTexte;
import com.ensah.AnnotationApp.entity.Dataset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CoupleTexteRepository extends JpaRepository<CoupleTexte, Integer> {
    List<CoupleTexte> findByDataset(Dataset dataset);
    Page<CoupleTexte> findByDataset(Dataset dataset, Pageable pageable);
    // Compter tous les couples d’un dataset donné
    long countByDataset(Dataset dataset);
}
