package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "faculty")
public class Faculty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFaculty;

    @Column(name = "nameFaculty", nullable = false, length = 30)
    private String nameFaculty;

    public Faculty() {
        super();
    }

    public Faculty(int idFaculty, @NotEmpty(message = "Debe ingresar nombre de Facultad") String nameFaculty) {
        super();
        this.idFaculty = idFaculty;
        this.nameFaculty = nameFaculty;
    }

    public int getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(int idFaculty) {
        this.idFaculty = idFaculty;
    }

    public String getNameFaculty() {
        return nameFaculty;
    }

    public void setNameFaculty(String nameFaculty) {
        this.nameFaculty = nameFaculty;
    }

}