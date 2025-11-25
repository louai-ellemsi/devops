package org.example.classtp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.classtp.entities.Bloc;
import org.example.classtp.service.IBlocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Gestion Bloc")
@RestController
@RequestMapping("/api/blocs")
public class BlocController {

    @Autowired
    private IBlocService blocService;

    @GetMapping
    public List<Bloc> getAllBlocs() {
        return blocService.getAllBlocs();
    }

    @GetMapping("/{id}")
    public Bloc getBlocById(@PathVariable Long id) {
        return blocService.getBlocById(id);
    }

    @PostMapping
    @Operation(description = "add some blocs :)")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @PutMapping("/{id}")
    public Bloc updateBloc(@PathVariable Long id, @RequestBody Bloc bloc) {
        return blocService.updateBloc(id, bloc);
    }

    @DeleteMapping("/{id}")
    public void deleteBloc(@PathVariable Long id) {
        blocService.deleteBloc(id);
    }

    @PutMapping("/affect/{idBloc}")
    public Bloc affectChambres(@PathVariable long idBloc, @RequestBody List<Long> numChambre) {
        return blocService.affecterChambresABloc(numChambre, idBloc);
    }
}
