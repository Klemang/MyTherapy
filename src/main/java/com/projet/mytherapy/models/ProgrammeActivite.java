package com.projet.mytherapy.models;

import jakarta.persistence.*;

@Entity
@Table(name = "programme_activite")
public class ProgrammeActivite {
    @EmbeddedId
    private ProgrammeActiviteId id;

    @MapsId("idActivite")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idActivite", nullable = false)
    private Activite activite;

    @MapsId("idProgramme")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idProgramme", nullable = false)
    private Programme programme;

    @Column(name = "note")
    private Integer note;

    @Column(name = "etat", nullable = false)
    private String etat;

    public ProgrammeActiviteId getId() {
        return id;
    }

    public void setId(ProgrammeActiviteId id) {
        this.id = id;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}