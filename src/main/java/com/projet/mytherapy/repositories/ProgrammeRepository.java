package com.projet.mytherapy.repositories;

import com.projet.mytherapy.models.Programme;
import com.projet.mytherapy.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Integer> {
    List<Programme> findAllByUtilisateur(Utilisateur utilisateur);

    List<Programme> findAllByUtilisateurAndEtat(Utilisateur utilisateur, String etat);

    Programme save(Programme programme);

    @Transactional
    @Modifying
    @Query("update Programme p set p.etat = :etat where p.id = :idProgramme")
    void updateEtatById(String etat, Integer idProgramme);

    @Transactional
    void deleteProgrammeById(Integer id);
}