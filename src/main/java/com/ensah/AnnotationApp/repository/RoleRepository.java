package com.ensah.AnnotationApp.repository;
import com.ensah.AnnotationApp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNomRole(Role.NomRole nomRole);
}

