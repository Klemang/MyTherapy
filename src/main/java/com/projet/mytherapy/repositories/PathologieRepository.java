package com.projet.mytherapy.repositories;

import com.projet.mytherapy.models.Pathologie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PathologieRepository extends JpaRepository<Pathologie, Integer> {
    List<Pathologie> findAll();
}