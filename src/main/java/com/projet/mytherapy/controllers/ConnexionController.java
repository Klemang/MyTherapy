package com.projet.mytherapy.controllers;

import com.projet.mytherapy.models.Utilisateur;
import com.projet.mytherapy.services.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/connexion")
public class ConnexionController {

    @GetMapping
    public String connexionPage(Model model) {
        return "connexion";
    }

    @PostMapping
    public String connexionUtilisateur(@RequestParam String email, @RequestParam String motdepasse, Model model, HttpSession session) {
        Utilisateur u = UtilisateurService.verifierConnexion(email, motdepasse);
        if (u != null) {
            session.setAttribute("utilisateur", u);
            return "redirect:";
        }
        else {
            model.addAttribute("error", "Mauvaise combinaison !");
            return "connexion";
        }
    }
}
