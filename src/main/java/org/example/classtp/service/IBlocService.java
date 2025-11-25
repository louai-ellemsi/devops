package org.example.classtp.service;

import org.example.classtp.entities.Bloc;
import java.util.List;

public interface IBlocService {
    List<Bloc> getAllBlocs();
    Bloc getBlocById(Long id);
    Bloc addBloc(Bloc bloc);
    Bloc updateBloc(Long id, Bloc bloc);
    void deleteBloc(Long id);
    Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);
}
