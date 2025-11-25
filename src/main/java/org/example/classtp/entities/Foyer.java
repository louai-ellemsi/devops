package org.example.classtp.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "foyer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Foyer  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foyer")
    private Long idFoyer;

    @Column(name = "nom_foyer")
    private String nomFoyer;

    @Column(name = "capacite_foyer")
    private Long capaciteFoyer;

    // Relation OneToOne avec Universite
    @OneToOne(mappedBy = "foyer")
    private Universite universite;

    // Relation OneToMany avec Bloc
    @OneToMany(mappedBy = "foyer", cascade = CascadeType.ALL)
    private Set<Bloc> blocs;
}