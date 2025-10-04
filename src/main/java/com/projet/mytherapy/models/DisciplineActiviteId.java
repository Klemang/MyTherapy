package com.projet.mytherapy.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class DisciplineActiviteId implements java.io.Serializable {

    @Column(name = "idDiscipline", nullable = false)
    private Integer idDiscipline;

    @Column(name = "idActivite", nullable = false)
    private Integer idActivite;

    public Integer getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(Integer idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public Integer getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DisciplineActiviteId entity = (DisciplineActiviteId) o;
        return Objects.equals(this.idActivite, entity.idActivite) &&
                Objects.equals(this.idDiscipline, entity.idDiscipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idActivite, idDiscipline);
    }

}