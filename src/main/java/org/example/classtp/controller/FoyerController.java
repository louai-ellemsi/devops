package org.example.classtp.controller;

import org.example.classtp.entities.Foyer;
import org.example.classtp.service.IFoyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foyers")
public class FoyerController {

    @Autowired
    private IFoyerService foyerService;

    @GetMapping
    public List<Foyer> getAll() { return foyerService.getAllFoyers(); }

    @GetMapping("/{id}")
    public Foyer getById(@PathVariable Long id) { return foyerService.getFoyerById(id); }

    @PostMapping
    public Foyer add(@RequestBody Foyer f) { return foyerService.addFoyer(f); }

    @PutMapping("/{id}")
    public Foyer update(@PathVariable Long id, @RequestBody Foyer f) { return foyerService.updateFoyer(id, f); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { foyerService.deleteFoyer(id); }

    @PostMapping("/add-with-universite/{idUniversite}")
    public Foyer ajouterEtAffecter(@RequestBody Foyer foyer, @PathVariable long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }
}
