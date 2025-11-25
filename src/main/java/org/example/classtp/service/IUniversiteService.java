package org.example.classtp.service;

import org.example.classtp.entities.Universite;
import java.util.List;

public interface IUniversiteService {
    List<Universite> getAll();
    Universite getById(Long idUniversite);
    Universite add(Universite universite);
    Universite update(Long idUniversite, Universite universite);
    void delete(Long idUniversite);
    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    Universite desaffecterFoyerAUniversite(long idUniversite);
    java.util.List<org.example.classtp.entities.Chambre> getChambresParNomUniversite(String nomUniversite);
}
