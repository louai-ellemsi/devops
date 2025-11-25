package org.example.classtp.repository;

import org.example.classtp.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite, Long> {
    Universite findById(long id);
    Universite findByNomUniversite(String nomUniversite);

}