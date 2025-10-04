package com.projet.mytherapy.services;

import com.projet.mytherapy.models.Programme;
import com.projet.mytherapy.models.Utilisateur;
import com.projet.mytherapy.repositories.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammeService {
    private final ProgrammeRepository programmeRepository;

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository) {
        this.programmeRepository = programmeRepository;
    };

    public List<Programme> recupererProgrammesUtilisateur(Utilisateur utilisateur, String etat) {
        return programmeRepository.findAllByUtilisateurAndEtat(utilisateur, etat);
    }

    public Programme creerProgramme(Programme programme) {
        programme.setEtat("EN_COURS");
        return programmeRepository.save(programme);
    }

    public void supprimerProgramme(int idProgramme) {
        programmeRepository.deleteProgrammeById(idProgramme);
    }

    public void finirProgramme(int idProgramme) {
        programmeRepository.updateEtatById("TERMINE", idProgramme);
    }
}
