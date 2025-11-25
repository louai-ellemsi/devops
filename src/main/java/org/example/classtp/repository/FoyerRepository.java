package org.example.classtp.repository;

import org.example.classtp.entities.Foyer;

import org.example.classtp.entities.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {

 List<Foyer> findByBlocsNomBlocAndBlocsCapaciteBloc(String nomBloc, Long capaciteBloc );
   }