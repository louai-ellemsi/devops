package org.example.classtp.controller;

import org.example.classtp.entities.Etudiant;
import org.example.classtp.service.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private IEtudiantService etudiantService;

    @GetMapping
    public List<Etudiant> getAll() { return etudiantService.getAllEtudiants(); }

    @GetMapping("/{id}")
    public Etudiant getById(@PathVariable Long id) { return etudiantService.getEtudiantById(id); }

    @PostMapping
    public Etudiant add(@RequestBody Etudiant e) { return etudiantService.addEtudiant(e); }

    @PutMapping("/{id}")
    public Etudiant update(@PathVariable Long id, @RequestBody Etudiant e) { return etudiantService.updateEtudiant(id, e); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { etudiantService.deleteEtudiant(id); }
}
