package org.example.classtp.repository;

import org.example.classtp.entities.Chambre;
import org.example.classtp.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByNumeroChambreIn(List<Long> numeroChambres);

    List<Chambre> findByBloc_IdBlocAndTypeC(Long idBloc, TypeChambre typeC);

    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findByBlocAndTypeC_JPQL(@Param("idBloc") Long idBloc, @Param("typeC") TypeChambre typeC);

    @Query("SELECT c FROM Chambre c WHERE c.idChambre NOT IN (SELECT c2.idChambre FROM Reservation r JOIN r.chambres c2 WHERE r.estValide = true AND FUNCTION('year', r.anneeUniversitaire) = :annee)")
    List<Chambre> findNonReservedForYear(@Param("annee") int annee);

    @Query("SELECT c FROM Chambre c JOIN c.bloc b JOIN b.foyer f JOIN Universite u ON u.foyer = f WHERE u.nomUniversite = :nom AND c.typeC = :type AND c.idChambre NOT IN (SELECT c2.idChambre FROM Reservation r JOIN r.chambres c2 WHERE r.estValide = true AND FUNCTION('year', r.anneeUniversitaire) = :annee)")
    List<Chambre> findNonReservedByUniversiteAndTypeForYear(@Param("nom") String nomUniversite, @Param("type") TypeChambre type, @Param("annee") int annee);
}