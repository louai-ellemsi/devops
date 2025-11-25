package org.example.classtp.service;

import org.example.classtp.entities.Reservation;
import java.util.List;

 public interface IReservationService {
    List<Reservation> getAll();
    Reservation getById(Long idReservation);
    Reservation add(Reservation reservation);
    Reservation update(Long idReservation, Reservation reservation);
    void delete(Long idReservation);
    Reservation ajouterReservation(long idBloc, long cinEtudiant);
    Reservation annulerReservation(long cinEtudiant);
    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(java.util.Date anneeUniversite, String nomUniversite);
}
