package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Dataset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String nomDataset;
    // Large Object : CLOB / BLOB dans BD
    @Lob
    private String description;
    @OneToMany(mappedBy = "dataset")
    private List<ClassePossible> classes;
    @OneToMany(mappedBy = "dataset")
    private List<Tache> taches;
    @OneToMany(mappedBy = "dataset", cascade = CascadeType.ALL)
    private List<CoupleTexte> couples;
}
