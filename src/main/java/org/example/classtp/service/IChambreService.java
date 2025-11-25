package org.example.classtp.service;

import org.example.classtp.entities.Chambre;
import org.example.classtp.entities.TypeChambre;

import java.util.List;

 public interface IChambreService {
    List<Chambre> getAllChambres();
    Chambre getChambreById(Long idChambre);
    Chambre addChambre(Chambre chambre);
    Chambre updateChambre(Long idChambre, Chambre chambre);
    void deleteChambre(Long idChambre);
    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);
    List<Chambre> getChambresNonReserveesCetteAnnee();
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);
}


