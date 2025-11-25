package org.example.classtp.service;

import org.example.classtp.entities.Foyer;
import org.example.classtp.entities.Bloc;
import org.example.classtp.entities.Chambre;
import org.example.classtp.entities.Universite;
import org.example.classtp.repository.FoyerRepository;
import org.example.classtp.repository.UniversiteRepository;
import org.example.classtp.service.IUniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversiteService implements IUniversiteService {

    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    private FoyerRepository foyerRepository;

    public List<Universite> getAll() { return universiteRepository.findAll(); }

    public Universite getById(Long id) { return universiteRepository.findById(id).orElse(null); }

    public Universite add(Universite u) { return universiteRepository.save(u); }

    public Universite update(Long id, Universite u) {
        Universite uni = universiteRepository.findById(id).orElse(null);
        if (uni != null) {
            uni.setNomUniversite(u.getNomUniversite());
            uni.setAdresse(u.getAdresse());
            return universiteRepository.save(uni);
        }
        return null;
    }

    public void delete(Long id) { universiteRepository.deleteById(id); }
    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        if (universite == null) {
            throw new RuntimeException("Universite not found");
        }
        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer not found"));
        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(Long.valueOf(idUniversite))
                .orElseThrow(() -> new RuntimeException("Universite not found"));
        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }

    @Override
    public java.util.List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        Universite universite = universiteRepository.findByNomUniversite(nomUniversite);
        if (universite == null) {
            throw new RuntimeException("Universite not found");
        }
        Foyer foyer = universite.getFoyer();
        java.util.List<Chambre> result = new java.util.ArrayList<>();
        if (foyer == null) {
            return result;
        }
        java.util.Set<Bloc> blocs = foyer.getBlocs();
        if (blocs == null) {
            return result;
        }
        for (Bloc b : blocs) {
            java.util.Set<Chambre> chambres = b.getChambres();
            if (chambres != null) {
                result.addAll(chambres);
            }
        }
        return result;
    }
}
