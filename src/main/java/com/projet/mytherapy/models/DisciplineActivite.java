package com.projet.mytherapy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "discipline_activite")
public class DisciplineActivite {
    @EmbeddedId
    private DisciplineActiviteId id;

    @MapsId("idDiscipline")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idDiscipline", nullable = false)
    private Discipline idDiscipline;

    @MapsId("idActivite")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idActivite", nullable = false)
    private Activite idActivite;

    public DisciplineActiviteId getId() {
        return id;
    }

    public void setId(DisciplineActiviteId id) {
        this.id = id;
    }

    public Discipline getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(Discipline idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public Activite getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Activite idActivite) {
        this.idActivite = idActivite;
    }

}