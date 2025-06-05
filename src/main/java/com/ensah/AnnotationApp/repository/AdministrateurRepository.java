package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurRepository extends JpaRepository<Administrateur, Integer> {
}
