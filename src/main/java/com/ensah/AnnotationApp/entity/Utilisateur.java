package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String prenom;
    @Column(unique = true, nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_Id", nullable = false)
    private Role role;
    private boolean actif = true;
}
