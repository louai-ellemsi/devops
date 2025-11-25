package org.example.classtp.service;

import org.example.classtp.entities.Chambre;
import org.example.classtp.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {

    List<Etudiant> getAllEtudiants();
    Etudiant getEtudiantById(Long idEtudiant);
    Etudiant addEtudiant(Etudiant etudiant);
    Etudiant updateEtudiant(Long idEtudiant, Etudiant etudiant);
    void deleteEtudiant(Long idEtudiant);

}
