package org.example.classtp.controller;

import org.example.classtp.entities.Universite;
import org.example.classtp.entities.Chambre;
import org.example.classtp.service.IUniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/universites")
public class UniversiteController {

    @Autowired
    IUniversiteService universiteService;

    @GetMapping
    public List<Universite> getAll() { return universiteService.getAll(); }

    @GetMapping("/{id}")
    public Universite getById(@PathVariable Long id) { return universiteService.getById(id); }

    @PostMapping
    public Universite add(@RequestBody Universite u) { return universiteService.add(u); }

    @PutMapping("/{id}")
    public Universite update(@PathVariable Long id, @RequestBody Universite u) { return universiteService.update(id, u); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { universiteService.delete(id); }
    @PutMapping("/affect/{nomUniversite}/{idFoyer}")
    public Universite affect(@PathVariable String nomUniversite,
                             @PathVariable Long idFoyer) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }

    @PutMapping("/desaffect/{idUniversite}")
    public Universite desaffect(@PathVariable Long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(idUniversite);
    }

    @GetMapping("/chambres/{nomUniversite}")
    public java.util.List<Chambre> getChambres(@PathVariable String nomUniversite) {
        return universiteService.getChambresParNomUniversite(nomUniversite);
    }

}
