package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "campus")
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCampus;

    @NotEmpty(message = "Debe ingresar nombre del campus")
    @Column(name = "nameCampus", nullable = false, length = 150)
    private String nameCampus;

    @NotEmpty(message = "Debe ingresar dirección del campus")
    @Column(name = "adressCampus", nullable = false, length = 150)
    private String adressCampus;

    public Campus() {
        super();
    }

    public Campus(int idCampus, @NotEmpty(message = "Debe ingresar nombre del campus") String nameCampus,
            @NotEmpty(message = "Debe ingresar dirección del campus") String adressCampus) {
        super();
        this.idCampus = idCampus;
        this.nameCampus = nameCampus;
        this.adressCampus = adressCampus;
    }

    public int getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(int idCampus) {
        this.idCampus = idCampus;
    }

    public String getNameCampus() {
        return nameCampus;
    }

    public void setNameCampus(String nameCampus) {
        this.nameCampus = nameCampus;
    }

    public String getAdressCampus() {
        return adressCampus;
    }

    public void setAdressCampus(String adressCampus) {
        this.adressCampus = adressCampus;
    }

}
