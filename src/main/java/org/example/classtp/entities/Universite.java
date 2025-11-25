package org.example.classtp.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "universite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Universite  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_universite")
    private Long idUniversite;

    @Column(name = "nom_universite")
    private String nomUniversite;

    @Column(name = "adresse")
    private String adresse;

    // Relation OneToOne avec Foyer
    @OneToOne
    @JoinColumn(name = "foyer_id")
    private Foyer foyer;
}