package com.projet.mytherapy.controllers;

import com.projet.mytherapy.models.*;
import com.projet.mytherapy.repositories.ProgrammeActiviteRepository;
import com.projet.mytherapy.services.ActiviteService;
import com.projet.mytherapy.services.ProgrammeActiviteService;
import com.projet.mytherapy.services.ProgrammeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/activites")
public class ActiviteController {


    private final ActiviteService activiteService;
    private final ProgrammeService programmeService;
    private final ProgrammeActiviteRepository programmeActiviteRepository;
    private final ProgrammeActiviteService programmeActiviteService;

    public ActiviteController(ActiviteService activiteService, ProgrammeService programmeService, ProgrammeActiviteRepository programmeActiviteRepository, ProgrammeActiviteService programmeActiviteService) {
        this.activiteService = activiteService;
        this.programmeService = programmeService;
        this.programmeActiviteRepository = programmeActiviteRepository;
        this.programmeActiviteService = programmeActiviteService;
    }

    @GetMapping
    public String activites(Model model, HttpSession session) {
        if (session != null && session.getAttribute("utilisateur") != null) {
            List<Activite> activites;

            Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
            if(u.getPathologie() == null) {
                activites = activiteService.recupererActivites();
            } else {
                activites = activiteService.recupererRecommandations(u.getPathologie().getId());
            }

            model.addAttribute("activites", activites);

            if (activites.isEmpty()) {
                model.addAttribute("info", "Aucune activité trouvée !");
            }

            return "activites";
        }
        return "connexion";
    }

    @PostMapping
    public String activitesNom(Model model, HttpSession session, @RequestParam(required = false) String nom) {
        if (session != null && session.getAttribute("utilisateur") != null) {
            List<Activite> activites;

            if (!nom.isEmpty()) {
                activites = activiteService.recupererParNom(nom);
            } else {
                Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
                if(u.getPathologie() == null) {
                    activites = activiteService.recupererActivites();
                } else {
                    activites = activiteService.recupererRecommandations(u.getPathologie().getId());
                }
            }

            model.addAttribute("activites", activites);

            if (activites.isEmpty()) {
                model.addAttribute("info", "Aucune activité trouvée !");
            }

            model.addAttribute("nom", nom);
            return "fragments/grille_recherche_activite :: grille";
        }
        return "connexion";
    }


    @PostMapping("/details/{id}")
    public String activiteDetails(@PathVariable int id, Model model, HttpSession session) {
        if (session != null && session.getAttribute("utilisateur") != null) {
            Activite activite = activiteService.recupererActivite(id);
            model.addAttribute("activite", activite);
            return "fragments/popupDetailsActivite :: popup";
        }
        return "connexion";
    }

    @PostMapping("/ajouter/{id}")
    public String activiteAjouter(@PathVariable int id, Model model, HttpSession session, @RequestParam(required = false) String programme) {
        if (session != null && session.getAttribute("utilisateur") != null) {
            if(programme == null) {
                Activite activite = activiteService.recupererActivite(id);
                List<Programme> programmes = programmeService.recupererProgrammesUtilisateur((Utilisateur) session.getAttribute("utilisateur"), "EN_COURS");
                model.addAttribute("activite", activite);
                model.addAttribute("programmes", programmes);
                return "fragments/popupAjoutActivite :: popup";
            }
            else {
                ProgrammeActiviteId paID = new ProgrammeActiviteId();
                paID.setIdActivite(id);
                paID.setIdProgramme(Integer.parseInt(programme));
                ProgrammeActivite pa = new ProgrammeActivite();
                pa.setId(paID);

                Activite acti = new Activite();
                acti.setId(id);

                Programme prog = new Programme();
                prog.setId(Integer.parseInt(programme));

                pa.setActivite(acti);
                pa.setProgramme(prog);

                pa.setEtat("EN_COURS");
                programmeActiviteService.ajouterActivite(pa);
                return "redirect:/programmes";
            }
        }
        return "connexion";
    }
}
