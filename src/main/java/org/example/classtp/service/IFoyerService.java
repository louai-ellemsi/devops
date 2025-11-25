package org.example.classtp.service;

import org.example.classtp.entities.Foyer;
import java.util.List;

public interface IFoyerService {
    List<Foyer> getAllFoyers();
    Foyer getFoyerById(Long idFoyer);
    Foyer addFoyer(Foyer foyer);
    Foyer updateFoyer(Long idFoyer, Foyer foyer);
    void deleteFoyer(Long idFoyer);
    Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite);
}
