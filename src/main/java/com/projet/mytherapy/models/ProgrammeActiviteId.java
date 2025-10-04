package com.projet.mytherapy.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class ProgrammeActiviteId implements java.io.Serializable {

    @Column(name = "idActivite", nullable = false)
    private Integer idActivite;

    @Column(name = "idProgramme", nullable = false)
    private Integer idProgramme;

    public Integer getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public Integer getIdProgramme() {
        return idProgramme;
    }

    public void setIdProgramme(Integer idProgramme) {
        this.idProgramme = idProgramme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProgrammeActiviteId entity = (ProgrammeActiviteId) o;
        return Objects.equals(this.idProgramme, entity.idProgramme) &&
                Objects.equals(this.idActivite, entity.idActivite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProgramme, idActivite);
    }

}