package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "nom_Role", nullable = false)
    private NomRole nomRole;
    public enum NomRole {
        USER_ROLE,
        ADMIN_ROLE
    }
}