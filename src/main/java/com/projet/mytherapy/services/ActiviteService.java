package com.projet.mytherapy.services;

import com.projet.mytherapy.models.Activite;
import com.projet.mytherapy.repositories.ActiviteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiviteService {
    private final ActiviteRepository activiteRepository;

    public ActiviteService(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    public List<Activite> recupererParNom(String nom) {
        return activiteRepository.findByNomContaining(nom);
    }

    public List<Activite> recupererRecommandations(Integer idPathologie) {
        return activiteRepository.findByPathologies_Id(idPathologie);
    }

    public Activite recupererActivite(Integer idActivite) {
        return activiteRepository.findActiviteById(idActivite);
    }

    public List<Activite> recupererActivites() {
        return activiteRepository.findAll();
    }
}
