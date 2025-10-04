package com.projet.mytherapy.repositories;

import com.projet.mytherapy.models.Programme;
import com.projet.mytherapy.models.ProgrammeActivite;
import com.projet.mytherapy.models.ProgrammeActiviteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProgrammeActiviteRepository extends JpaRepository<ProgrammeActivite, Integer> {
    ProgrammeActivite save(ProgrammeActivite programmeActivite);

    List<ProgrammeActivite> findAllByProgramme(Programme programme);

    @Transactional
    void deleteProgrammeActivitesById(ProgrammeActiviteId id);

    @Transactional
    @Modifying
    @Query("update ProgrammeActivite pa set pa.etat = :etat where pa.programme.id = :idProgramme and pa.activite.id = :idActivite")
    void updateEtatById(String etat, Integer idProgramme, Integer idActivite);

    @Transactional
    @Modifying
    @Query("update ProgrammeActivite pa set pa.note = :note where pa.programme.id = :idProgramme and pa.activite.id = :idActivite")
    void updateNoteById(Integer note, Integer idProgramme, Integer idActivite);
}