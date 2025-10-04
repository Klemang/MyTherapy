package com.projet.mytherapy.services;

import com.projet.mytherapy.models.Programme;
import com.projet.mytherapy.models.ProgrammeActivite;
import com.projet.mytherapy.models.ProgrammeActiviteId;
import com.projet.mytherapy.models.Utilisateur;
import com.projet.mytherapy.repositories.ProgrammeActiviteRepository;
import com.projet.mytherapy.repositories.ProgrammeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgrammeActiviteService {
    private final ProgrammeActiviteRepository programmeActiviteRepository;
    private final ProgrammeRepository programmeRepository;

    public ProgrammeActiviteService(ProgrammeActiviteRepository programmeActiviteRepository, ProgrammeRepository programmeRepository) {
        this.programmeActiviteRepository = programmeActiviteRepository;
        this.programmeRepository = programmeRepository;
    }

    public ProgrammeActivite ajouterActivite(ProgrammeActivite programmeActivite) {
        return programmeActiviteRepository.save(programmeActivite);
    }

    public List<ProgrammeActivite> recupererActivites(Programme programme) {
        return programmeActiviteRepository.findAllByProgramme(programme);
    }

    public List<Map<Programme, List<ProgrammeActivite>>> recuperProgrammesActivites(Utilisateur utilisateur) {
        List<Programme> programmes = programmeRepository.findAllByUtilisateur(utilisateur);


        Map<Programme, List<ProgrammeActivite>> progactivites = new HashMap<>();
        for (Programme prog : programmes) {
            List<ProgrammeActivite> activites = programmeActiviteRepository.findAllByProgramme(prog);

            progactivites.put(prog, activites);
        }



        Map<Programme, List<ProgrammeActivite>> prog_en_cours = new HashMap<>();
        Map<Programme, List<ProgrammeActivite>> prog_finis = new HashMap<>();

        for(Programme prog : progactivites.keySet()) {
            if(prog.getEtat().equals("EN_COURS")) {
                prog_en_cours.put(prog, progactivites.get(prog));
            } else {
                prog_finis.put(prog, progactivites.get(prog));
            }
        }

        List<Map<Programme, List<ProgrammeActivite>>> progs = new ArrayList<>();
        progs.add(prog_en_cours);
        progs.add(prog_finis);
        return progs;
    }

    public void supprimerActivite(ProgrammeActiviteId id) {
        programmeActiviteRepository.deleteProgrammeActivitesById(id);
    }

    public void validerActivite(Integer idProgramme, Integer idActivite, Integer note) {
        programmeActiviteRepository.updateEtatById("TERMINE", idProgramme, idActivite);
        programmeActiviteRepository.updateNoteById(note, idProgramme, idActivite);
    }
}
