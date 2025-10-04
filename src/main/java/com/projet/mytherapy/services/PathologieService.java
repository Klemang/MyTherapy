package com.projet.mytherapy.services;

import com.projet.mytherapy.models.Pathologie;
import com.projet.mytherapy.repositories.PathologieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathologieService {
    private final PathologieRepository pathologieRepository;

    @Autowired
    public PathologieService(PathologieRepository pathologieRepository) {
        this.pathologieRepository = pathologieRepository;
    };

    public List<Pathologie> recupererPathologies() {
        return pathologieRepository.findAll();
    }
}
