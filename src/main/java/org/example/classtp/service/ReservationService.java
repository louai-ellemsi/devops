package org.example.classtp.service;

import org.example.classtp.entities.Reservation;
import org.example.classtp.entities.Bloc;
import org.example.classtp.entities.Chambre;
import org.example.classtp.entities.Etudiant;
import org.example.classtp.entities.TypeChambre;
import org.example.classtp.repository.ReservationRepository;
import org.example.classtp.repository.BlocRepository;
import org.example.classtp.repository.ChambreRepository;
import org.example.classtp.repository.EtudiantRepository;
import org.example.classtp.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;
import java.util.Set;
import java.util.HashSet;
import java.util.Calendar;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    BlocRepository blocRepository;
    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    EtudiantRepository etudiantRepository;

    public List<Reservation> getAll() { return reservationRepository.findAll(); }

    public Reservation getById(Long id) { return reservationRepository.findById(id).orElse(null); }

    public Reservation add(Reservation r) { return reservationRepository.save(r); }

    public Reservation update(Long id, Reservation r) {
        Reservation res = reservationRepository.findById(id).orElse(null);
        if (res != null) {
            res.setAnneeUniversitaire(r.getAnneeUniversitaire());
            res.setEstValide(r.getEstValide());
            return reservationRepository.save(res);
        }
        return null;
    }

    public void delete(Long id) { reservationRepository.deleteById(id); }

    @Override
    @Transactional
    public Reservation ajouterReservation(long idBloc, long cinEtudiant) {
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(() -> new RuntimeException("Bloc not found"));
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);
        if (etudiant == null) {
            throw new RuntimeException("Etudiant not found");
        }
        Chambre chambreDisponible = null;
        for (Chambre c : bloc.getChambres()) {
            TypeChambre type = c.getTypeC();
            if (type == null) {
                continue;
            }
            int capaciteMax = switch (type) {
                case SIMPLE -> 1;
                case DOUBLE -> 2;
                case TRIPLE -> 3;
            };
            int annee = Calendar.getInstance().get(Calendar.YEAR);
            int occ = 0;
            for (Reservation r : reservationRepository.findAll()) {
                if (r.getChambres() != null && r.getChambres().contains(c) && r.getEstValide()) {
                    Date d = r.getAnneeUniversitaire();
                    if (d != null) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(d);
                        if (cal.get(Calendar.YEAR) == annee) {
                            occ++;
                        }
                    }
                }
            }
            if (occ < capaciteMax) {
                chambreDisponible = c;
                break;
            }
        }
        if (chambreDisponible == null) {
            throw new RuntimeException("Aucune chambre disponible ou type manquant dans le bloc");
        }
        Reservation reservation = new Reservation();
        Date now = new Date();
        reservation.setAnneeUniversitaire(now);
        reservation.setEstValide(true);
        Set<Chambre> cs = new HashSet<>();
        cs.add(chambreDisponible);
        reservation.setChambres(cs);
        String num = chambreDisponible.getNumeroChambre() + "-" + bloc.getNomBloc() + "-" + Calendar.getInstance().get(Calendar.YEAR);
        reservation.setNumReservation(num);
        Reservation saved = reservationRepository.save(reservation);
        Set<Reservation> etuRes = etudiant.getReservations();
        if (etuRes == null) {
            etuRes = new HashSet<>();
        }
        etuRes.add(saved);
        etudiant.setReservations(etuRes);
        etudiantRepository.save(etudiant);
        return saved;
    }

    @Override
    @Transactional
    public Reservation annulerReservation(long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant);
        if (etudiant == null) {
            throw new RuntimeException("Etudiant not found");
        }
        Reservation cible = null;
        if (etudiant.getReservations() != null) {
            for (Reservation r : etudiant.getReservations()) {
                if (r.getEstValide()) {
                    cible = r;
                    break;
                }
            }
        }
        if (cible == null) {
            throw new RuntimeException("No active reservation for student");
        }
        cible.setEstValide(false);
        if (cible.getChambres() != null) {
            cible.getChambres().clear();
        }
        if (etudiant.getReservations() != null) {
            etudiant.getReservations().remove(cible);
        }
        etudiantRepository.save(etudiant);
        return reservationRepository.save(cible);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(anneeUniversite);
        int year = cal.get(java.util.Calendar.YEAR);
        return reservationRepository.findByUniversiteAndYear(nomUniversite, year);
    }
}
