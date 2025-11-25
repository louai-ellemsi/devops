package org.example.classtp.service;

import org.example.classtp.entities.Bloc;
import org.example.classtp.entities.Chambre;
import org.example.classtp.repository.BlocRepository;
import org.example.classtp.repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
 

@Service
public class BlocService implements IBlocService {

    @Autowired
    private BlocRepository blocRepository;
    @Autowired
    private ChambreRepository chambreRepository;

    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    public Bloc getBlocById(Long id) {
        return blocRepository.findById(id).orElse(null);
    }

    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    public Bloc updateBloc(Long id, Bloc bloc) {
        Bloc b = blocRepository.findById(id).orElse(null);
        if (b != null) {
            b.setNomBloc(bloc.getNomBloc());
            b.setCapaciteBloc(bloc.getCapaciteBloc());
            b.setFoyer(bloc.getFoyer());
            return blocRepository.save(b);
        }
        return null;
    }

    public void deleteBloc(Long id) {
        blocRepository.deleteById(id);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(() -> new RuntimeException("Bloc not found"));
        List<Chambre> chambres = chambreRepository.findByNumeroChambreIn(numChambre);
        for (Chambre c : chambres) {
            c.setBloc(bloc);
        }
        chambreRepository.saveAll(chambres);
        return bloc;
    }
}
