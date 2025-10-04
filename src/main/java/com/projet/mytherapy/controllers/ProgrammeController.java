package com.projet.mytherapy.controllers;

import com.projet.mytherapy.models.*;
import com.projet.mytherapy.services.ActiviteService;
import com.projet.mytherapy.services.ProgrammeActiviteService;
import com.projet.mytherapy.services.ProgrammeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

;


@Controller
@RequestMapping("/programmes")
public class ProgrammeController {

    private final ProgrammeService programmeService;
    private final ActiviteService activiteService;
    private final ProgrammeActiviteService programmeActiviteService;

    public ProgrammeController(ProgrammeService programmeService, ActiviteService activiteService, ProgrammeActiviteService programmeActiviteService) {
        this.programmeService = programmeService;
        this.activiteService = activiteService;
        this.programmeActiviteService = programmeActiviteService;
    }

    @GetMapping
    public String programmes(Model model, HttpSession session) {
        if (session != null && session.getAttribute("utilisateur") != null) {
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

            List<Map<Programme, List<ProgrammeActivite>>> progs = programmeActiviteService.recuperProgrammesActivites(utilisateur);

            model.addAttribute("prog_en_cours", progs.get(0));
            model.addAttribute("prog_finis", progs.get(1));
            return "programmes";
        }

        return "connexion";
    }

    @PostMapping("/creer")
    public String creerProgramme(Model model, HttpSession session, @RequestParam String nom, @RequestParam(required = false) Integer idActivite) {
        if (session != null && session.getAttribute("utilisateur") != null) {
            Programme p = new Programme();
            p.setNom(nom);
            p.setUtilisateur((Utilisateur) session.getAttribute("utilisateur"));

            programmeService.creerProgramme(p);

            if(idActivite != null) {
                Activite activite = activiteService.recupererActivite(idActivite);
                List<Programme> programmes = programmeService.recupererProgrammesUtilisateur((Utilisateur) session.getAttribute("utilisateur"), "EN_COURS");
                model.addAttribute("activite", activite);
                model.addAttribute("programmes", programmes);
                return "fragments/popupAjoutActivite :: popup";
            }
            else {
                return "programmes";
            }
        }
        return "connexion";
    }


    @PostMapping("/traitement/{type}")
    public String traitementProgramems(Model model, HttpSession session, @PathVariable String type, @RequestParam Integer idProgramme, @RequestParam Integer idActivite, @RequestParam(required = false) Integer note) {
        ProgrammeActiviteId id = new ProgrammeActiviteId();
        id.setIdProgramme(idProgramme);
        id.setIdActivite(idActivite);

        if(type.equals("suppr_programme")) {
            programmeService.supprimerProgramme(idProgramme);
        }
        else if (type.equals("suppr_activite")) {
            programmeActiviteService.supprimerActivite(id);
        }
        else if (type.equals("valider_activite")) {
            programmeActiviteService.validerActivite(idProgramme, idActivite, note);

            Programme p = new Programme();
            p.setId(idProgramme);
            List<ProgrammeActivite> paListe = programmeActiviteService.recupererActivites(p);

            boolean estFini = true;
            for(ProgrammeActivite pa : paListe) {
                if (pa.getEtat().equals("EN_COURS")) {
                    estFini = false;
                    break;
                }
            }

            if (estFini) {
                programmeService.finirProgramme(idProgramme);
            }
        }
        return "/programmes";
    }
}
