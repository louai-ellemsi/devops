package org.example.classtp.service;

import org.example.classtp.entities.Foyer;
import org.example.classtp.entities.Bloc;
import org.example.classtp.entities.Universite;
import org.example.classtp.repository.FoyerRepository;
import org.example.classtp.repository.UniversiteRepository;
import org.example.classtp.service.IFoyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoyerService implements IFoyerService {

    @Autowired
    private FoyerRepository foyerRepository;
    @Autowired
    private UniversiteRepository universiteRepository;

    public List<Foyer> getAllFoyers() { return foyerRepository.findAll(); }

    public Foyer getFoyerById(Long id) { return foyerRepository.findById(id).orElse(null); }

    public Foyer addFoyer(Foyer foyer) { return foyerRepository.save(foyer); }

    public Foyer updateFoyer(Long id, Foyer foyer) {
        Foyer f = foyerRepository.findById(id).orElse(null);
        if (f != null) {
            f.setNomFoyer(foyer.getNomFoyer());
            f.setCapaciteFoyer(foyer.getCapaciteFoyer());
            return foyerRepository.save(f);
        }
        return null;
    }

    public void deleteFoyer(Long id) { foyerRepository.deleteById(id); }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        if (foyer.getBlocs() != null) {
            for (Bloc b : foyer.getBlocs()) {
                b.setFoyer(foyer);
            }
        }
        Foyer saved = foyerRepository.save(foyer);
        Universite universite = universiteRepository.findById(idUniversite);
        universite.setFoyer(saved);
        universiteRepository.save(universite);
        return saved;
    }
}
