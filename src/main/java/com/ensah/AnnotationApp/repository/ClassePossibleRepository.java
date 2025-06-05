package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.ClassePossible;
import com.ensah.AnnotationApp.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClassePossibleRepository extends JpaRepository<ClassePossible, Integer> {
    List<ClassePossible> findByDataset(Dataset dataset);
}