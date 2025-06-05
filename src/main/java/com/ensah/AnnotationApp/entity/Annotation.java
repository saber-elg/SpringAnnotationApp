package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Annotation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "classeChoisie_Id")
    private ClassePossible classeChoisie;
    @OneToOne
    @JoinColumn(name = "coupleTexte_Id", unique = true) // un seul coupleTexte par annotation
    private CoupleTexte couple;
    @ManyToOne
    @JoinColumn(name = "annotateur_Id")
    private Annotateur annotateur;
}

