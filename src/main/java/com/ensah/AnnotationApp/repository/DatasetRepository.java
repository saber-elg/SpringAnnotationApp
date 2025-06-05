package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.Dataset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasetRepository extends JpaRepository<Dataset, Integer> {
}