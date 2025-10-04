package com.projet.mytherapy.repositories;

import com.projet.mytherapy.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur save(Utilisateur utilisateur);

    Utilisateur findByEmail(String email);

    Utilisateur findUtilisateurById(int idUtilisateur);

    void deleteUtilisateurById(int idUtilisateur);
}
