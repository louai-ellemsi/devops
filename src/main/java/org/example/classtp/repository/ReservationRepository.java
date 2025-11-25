package org.example.classtp.repository;

import org.example.classtp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("SELECT DISTINCT r FROM Reservation r JOIN r.chambres c JOIN c.bloc b JOIN b.foyer f JOIN Universite u ON u.foyer = f WHERE u.nomUniversite = :nom AND FUNCTION('year', r.anneeUniversitaire) = :annee")
    List<Reservation> findByUniversiteAndYear(@Param("nom") String nomUniversite, @Param("annee") int annee);
}