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

;
import java.util.Map;


@Controller
@RequestMapping("/compte")
public class CompteController {

    public final UtilisateurService utilisateurService;
    public final PathologieService pathologieService;

    @Autowired
    public CompteController(UtilisateurService utilisateurService, PathologieService pathologieService) {
        this.utilisateurService = utilisateurService;
        this.pathologieService = pathologieService;
    }


    @GetMapping({"/{section}", ""})
    public String compte(Model model, HttpSession session, @PathVariable(required = false) String section) {
        if (session.getAttribute("utilisateur") != null) {
            if(section == null || section.equals("profil")) {
                section = "profil";
                model.addAttribute("pathologies", pathologieService.recupererPathologies());
            }
            model.addAttribute("section", section);
            return "compte";
        } else {
            return "connexion";
        }
    }

    @PostMapping("/profil")
    public String modifierProfil(Model model, HttpSession session, @RequestParam String pathologie, @RequestParam Map<String, String> params) {
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        if (!params.get("email").equals(user.getEmail()) && UtilisateurService.emailExisteDeja(params.get("email"))) {
            model.addAttribute("error", "Impossible de modifier le mail !");
        } else {
            Utilisateur utilisateur = UtilisateurService.recuperer(user.getId());

            utilisateur.setPrenom(params.get("prenom"));
            utilisateur.setNom(params.get("nom"));
            utilisateur.setAge(Integer.parseInt(params.get("age")));
            utilisateur.setEmail(params.get("email"));

            if (pathologie.equals("0")) {
                utilisateur.setPathologie(null);
            } else {
                Pathologie patho = new Pathologie();
                patho.setId(Integer.parseInt(pathologie));
                utilisateur.setPathologie(patho);
            }

            Utilisateur u = UtilisateurService.ajouter(utilisateur, false);
            session.setAttribute("utilisateur", u);
            model.addAttribute("success", "Les modifications ont bien été enregistrées !");
        }
        model.addAttribute("pathologies", pathologieService.recupererPathologies());
        model.addAttribute("section", "profil");
        return "compte";
    }


    @PostMapping("/mdp")
    public String modifierMotDePasse(Model model, HttpSession session, @RequestParam Map<String, String> params) {
        Utilisateur user = (Utilisateur) session.getAttribute("utilisateur");
        if (UtilisateurService.verifierConnexion(user.getEmail(), params.get("mdp_actuel")) != null) {
            if(!params.get("mdp_actuel").equals(params.get("mdp_nouveau"))) {
                Utilisateur utilisateur = UtilisateurService.recuperer(user.getId());

                utilisateur.setMotdepasse(params.get("mdp_nouveau"));
                Utilisateur u = UtilisateurService.ajouter(utilisateur);
                session.setAttribute("utilisateur", u);

                model.addAttribute("success", "Le mot de passe a bien été modifié !");
            } else {
                model.addAttribute("error", "Le nouveau mot de passe doit être différent de l'ancien !");
            }
        } else {
            model.addAttribute("error", "Impossible de modifier le mot de passe !");
        }
        model.addAttribute("section", "mdp");
        return "compte";
    }

    @PostMapping("/profil/suppression")
    public String supprimerCompte(HttpSession session) {
        utilisateurService.supprimer((Utilisateur) session.getAttribute("utilisateur"));
        session.invalidate();
        return "accueil";
    }
}