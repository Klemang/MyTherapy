package com.projet.mytherapy.services;

import com.projet.mytherapy.models.Utilisateur;
import com.projet.mytherapy.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilisateurService {

    private static UtilisateurRepository utilisateurRepository = null;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        UtilisateurService.utilisateurRepository = utilisateurRepository;
    }

    public static Utilisateur ajouter(Utilisateur utilisateur) {
        utilisateur.setMotdepasse(new BCryptPasswordEncoder().encode(utilisateur.getMotdepasse()));
        return utilisateurRepository.save(utilisateur);
    }

    public static Utilisateur ajouter(Utilisateur utilisateur, boolean encoderMdp) {
        if(encoderMdp) {
            utilisateur.setMotdepasse(new BCryptPasswordEncoder().encode(utilisateur.getMotdepasse()));
        }
        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public void supprimer(Utilisateur utilisateur) {
        utilisateurRepository.deleteUtilisateurById(utilisateur.getId()    );
    }

    public static Utilisateur recuperer(int idUtilisateur) {
        Utilisateur u = utilisateurRepository.findUtilisateurById(idUtilisateur);
        return u;
    }

    public static Utilisateur verifierConnexion(String email, String mdp) {
        Utilisateur u = utilisateurRepository.findByEmail(email);
        if(u != null) {
            if(new BCryptPasswordEncoder().matches(mdp, u.getMotdepasse())) {
                return u;
            }
        }
        return null;

    }

    public static boolean emailExisteDeja(String email) {
        return utilisateurRepository.findByEmail(email) != null;
    }
}
