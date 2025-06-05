package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Date delaiLimite;
    @ManyToOne
    @JoinColumn(name = "dataset_Id", nullable = false)
    private Dataset dataset;
    @ManyToOne
    @JoinColumn(name = "annotateurId")
    private Annotateur annotateur;
    @ManyToMany
    @JoinTable(
            name = "tache_couple_texte",
            joinColumns = @JoinColumn(name = "tache_id"),
            inverseJoinColumns = @JoinColumn(name = "couple_texte_id", unique = true)
    )
    private List<CoupleTexte> couples;
    @Transient
    public int getTaille() {
        return (couples != null) ? couples.size() : 0;
    }
    //@Transient signifie que ce champ ne sera pas stocké en base de données, mais uniquement accessible côté Java.
}
