package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;
//On utilisera lombok @Data pour générer automatiquement getter/setter/constructeurs ➔ très pratique.
@Entity
@Data
public class Administrateur {
    @Id
    @Column(name = "utilisateur_Id")
    private Integer utilisateurId;
    @OneToOne
    @JoinColumn(name = "utilisateur_Id")
    @MapsId
    private Utilisateur utilisateur;
}
