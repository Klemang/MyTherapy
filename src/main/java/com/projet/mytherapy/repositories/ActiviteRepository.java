package com.projet.mytherapy.repositories;

import com.projet.mytherapy.models.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Integer> {

    List<Activite> findByNomContaining(String nom);

    List<Activite> findByPathologies_Id(Integer idPathologie);

    Activite findActiviteById(Integer id);

    List<Activite> findAll();
}
