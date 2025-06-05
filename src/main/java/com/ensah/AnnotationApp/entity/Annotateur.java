package com.ensah.AnnotationApp.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Annotateur {
    @Id
    private Integer utilisateurId;
    @OneToOne
    @JoinColumn(name = "utilisateur_Id")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "annotateur")
    private List<Tache> taches;
    @OneToMany(mappedBy = "annotateur")
    private List<Annotation> annotations;
}
