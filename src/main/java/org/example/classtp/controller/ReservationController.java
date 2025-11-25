package org.example.classtp.controller;

import org.example.classtp.entities.Reservation;
import org.example.classtp.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    IReservationService reservationService;

    @GetMapping
    public List<Reservation> getAll() { return reservationService.getAll(); }

    @GetMapping("/{id}")
    public Reservation getById(@PathVariable Long id) { return reservationService.getById(id); }

    @PostMapping
    public Reservation add(@RequestBody Reservation r) { return reservationService.add(r); }

    @PutMapping("/{id}")
    public Reservation update(@PathVariable Long id, @RequestBody Reservation r) { return reservationService.update(id, r); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { reservationService.delete(id); }

    @PostMapping("/add/{idBloc}/{cinEtudiant}")
    public Reservation ajouter(@PathVariable long idBloc, @PathVariable long cinEtudiant) {
        return reservationService.ajouterReservation(idBloc, cinEtudiant);
    }

    @PutMapping("/annuler/{cinEtudiant}")
    public Reservation annuler(@PathVariable long cinEtudiant) {
        return reservationService.annulerReservation(cinEtudiant);
    }

    @GetMapping("/by-universite/{nomUniversite}/by-annee/{annee}")
    public List<Reservation> byUniversiteAndAnnee(@PathVariable String nomUniversite, @PathVariable int annee) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(java.util.Calendar.YEAR, annee);
        cal.set(java.util.Calendar.MONTH, 0);
        cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
        java.util.Date date = cal.getTime();
        return reservationService.getReservationParAnneeUniversitaireEtNomUniversite(date, nomUniversite);
    }
}
