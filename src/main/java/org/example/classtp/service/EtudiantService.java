package org.example.classtp.service;

import org.example.classtp.entities.Etudiant;
import org.example.classtp.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService implements IEtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant addEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
        Etudiant e = etudiantRepository.findById(id).orElse(null);
        if (e != null) {
            e.setNomEt(etudiant.getNomEt());
            e.setPrenomEt(etudiant.getPrenomEt());
            e.setCin(etudiant.getCin());
            e.setDateNaissance(etudiant.getDateNaissance());
            return etudiantRepository.save(e);
        }
        return null;
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
}
