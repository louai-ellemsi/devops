package org.example.classtp.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "chambre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chambre  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chambre")
    private Long idChambre;

    @Column(name = "numero_chambre")
    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_chambre")
    private TypeChambre typeC;

    // Relation ManyToOne avec Bloc
    @ManyToOne
    @JoinColumn(name = "bloc_id")
    private Bloc bloc;

    // Relation ManyToMany avec Reservation
    @ManyToMany(mappedBy = "chambres")
    private Set<Reservation> reservations;
}