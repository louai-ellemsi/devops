package org.example.classtp.service;

import org.example.classtp.entities.Chambre;
import org.example.classtp.entities.TypeChambre;
import org.example.classtp.repository.ChambreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChambreService implements IChambreService {

    @Autowired
    private ChambreRepository chambreRepository;

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre getChambreById(Long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public Chambre addChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre updateChambre(Long idChambre, Chambre chambre) {
        Chambre c = chambreRepository.findById(idChambre).orElse(null);
        if (c != null) {
            c.setNumeroChambre(chambre.getNumeroChambre());
            c.setTypeC(chambre.getTypeC());
            c.setBloc(chambre.getBloc());
            return chambreRepository.save(c);
        }
        return null;
    }

    @Override
    public void deleteChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBloc_IdBlocAndTypeC(idBloc, typeC);
    }

    @Override
    public List<Chambre> getChambresNonReserveesCetteAnnee() {
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return chambreRepository.findNonReservedForYear(year);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        return chambreRepository.findNonReservedByUniversiteAndTypeForYear(nomUniversite, type, year);
    }
}
