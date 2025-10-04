package com.projet.mytherapy.controllers;

import com.projet.mytherapy.models.Pathologie;
import com.projet.mytherapy.models.Utilisateur;
import com.projet.mytherapy.services.PathologieService;
import com.projet.mytherapy.services.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inscription")
public class InscriptionController {

    private final PathologieService pathologieService;

    @Autowired
    public InscriptionController(PathologieService pathologieService) {
        this.pathologieService = pathologieService;
    }

    @GetMapping
    public String inscriptionPage(Model model) {
        model.addAttribute("pathologies", pathologieService.recupererPathologies());
        return "inscription";
    }

    @PostMapping
    public String inscriptionUtilisateur(@RequestParam String pathologie, @ModelAttribute Utilisateur utilisateur, Model model, HttpSession session) {
        if(UtilisateurService.emailExisteDeja(utilisateur.getEmail())) {
            model.addAttribute("error", "Un compte associé à cet email existe déjà !");
            return "inscription";
        }
        else {
            if(pathologie.equals("0")) {
                utilisateur.setPathologie(null);
            }
            else {
                Pathologie patho = new Pathologie();
                patho.setId(Integer.parseInt(pathologie));
                utilisateur.setPathologie(patho);
            }
            utilisateur.setRole("USER");
            Utilisateur u = UtilisateurService.ajouter(utilisateur);
            session.setAttribute("utilisateur", u);
            return "accueil";
        }
    }
}
