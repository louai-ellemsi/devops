package org.example.classtp.controller;

import org.example.classtp.entities.Chambre;
import org.example.classtp.entities.TypeChambre;
import org.example.classtp.service.IChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chambres")
public class ChambreController {

    @Autowired
    private IChambreService chambreService;

    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    @GetMapping("/{id}")
    public Chambre getChambreById(@PathVariable Long id) {
        return chambreService.getChambreById(id);
    }

    @PostMapping
    public Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addChambre(chambre);
    }

    @PutMapping("/{id}")
    public Chambre updateChambre(@PathVariable Long id, @RequestBody Chambre chambre) {
        return chambreService.updateChambre(id, chambre);
    }

    @DeleteMapping("/{id}")
    public void deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
    }

    @GetMapping("/by-bloc/{idBloc}/by-type/{typeC}")
    public List<Chambre> getByBlocAndType(@PathVariable long idBloc, @PathVariable TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(idBloc, typeC);
    }

    @GetMapping("/non-reservees")
    public List<Chambre> getNonReservees() {
        return chambreService.getChambresNonReserveesCetteAnnee();
    }

    @GetMapping("/non-reservees/universite/{nomUniversite}/type/{type}")
    public List<Chambre> getNonReserveesByUniversiteAndType(@PathVariable String nomUniversite, @PathVariable TypeChambre type) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);
    }
}
