package org.example.classtp.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "bloc")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bloc {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bloc")
    private Long idBloc;

    @Column(name = "nom_bloc")
    private String nomBloc;

    @Column(name = "capacite_bloc")
    private Long capaciteBloc;

    // Relation ManyToOne avec Foyer
    @ManyToOne
    @JoinColumn(name = "foyer_id")
    private Foyer foyer;

    // Relation OneToMany avec Chambre
    @OneToMany(mappedBy = "bloc", cascade = CascadeType.ALL)
    private Set<Chambre> chambres;


}