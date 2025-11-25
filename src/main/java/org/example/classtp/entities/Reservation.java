package org.example.classtp.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private long idReservation;

    @Column(name = "num_reservation")
    private String numReservation;

    @Temporal(TemporalType.DATE)
    @Column(name = "annee_universitaire")
    private Date anneeUniversitaire;

    @Column(name = "est_valide")
    private boolean estValide;

    // Relation ManyToMany avec Chambre
    @ManyToMany
    @JoinTable(
            name = "reservation_chambre",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "chambre_id")
    )
    private Set<Chambre> chambres;

    // Relation ManyToMany avec Etudiant
    @ManyToMany(mappedBy = "reservations")
    private Set<Etudiant> etudiants;

    public boolean getEstValide() {
        return estValide;
    }
}