package com.projet.mytherapy.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "activite")
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idActivite", nullable = false)
    private Integer id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "adresse", nullable = false)
    private String adresse;

    @ManyToMany
    @JoinTable(name = "discipline_activite",
            joinColumns = @JoinColumn(name = "idActivite"),
            inverseJoinColumns = @JoinColumn(name = "idDiscipline"))
    private Set<Discipline> disciplines = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "pathologie_activite",
            joinColumns = @JoinColumn(name = "idActivite"),
            inverseJoinColumns = @JoinColumn(name = "idPathologie"))
    private Set<Pathologie> pathologies = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Set<com.projet.mytherapy.models.Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(Set<com.projet.mytherapy.models.Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Set<com.projet.mytherapy.models.Pathologie> getPathologies() {
        return pathologies;
    }

    public void setPathologies(Set<com.projet.mytherapy.models.Pathologie> pathologies) {
        this.pathologies = pathologies;
    }

}