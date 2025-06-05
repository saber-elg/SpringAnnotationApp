package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CoupleTexte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "texte1", columnDefinition = "TEXT")
    private String texte1;
    @Column(name = "texte2", columnDefinition = "TEXT")
    private String texte2;
    @ManyToOne
    @JoinColumn(name = "dataset_id", nullable = false)
    private Dataset dataset;
}
