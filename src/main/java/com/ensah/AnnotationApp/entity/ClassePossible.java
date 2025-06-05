package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ClassePossible {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String intituleClasse;
    @ManyToOne
    @JoinColumn(name = "dataset_Id", nullable = false)
    private Dataset dataset;
}
