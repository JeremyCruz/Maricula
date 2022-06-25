package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTeacher;

    @NotEmpty(message = "Debe ingresar nombre del profesor")
    @Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "El nombre no puede contener caracteres especiales")
    @Pattern(regexp = "[^0-9]+", message = "El nombre no puede contener un número")
    @Column(name = "nameTeacher", nullable = false, length = 150)
    private String nameTeacher;

    @NotEmpty(message = "Debe ingresar profesión del profesor")
    @Pattern(regexp = "[^!\"#$%&'()*+,-./:;<=>?@^_`{|}~]+", message = "La especialidad no puede contener caracteres especiales")
    @Pattern(regexp = "[^0-9]+", message = "La especialidad no puede contener un número")
    @Column(name = "specializationTeacher", nullable = false, length = 150)
    private String specializationTeacher;

    public Teacher() {
        super();
    }

    public Teacher(int idTeacher, @NotEmpty(message = "Debe ingresar nombre del profesor") String nameTeacher,
            @NotEmpty(message = "Debe ingresar profesión del profesor") String specializationTeacher) {
        this.idTeacher = idTeacher;
        this.nameTeacher = nameTeacher;
        this.specializationTeacher = specializationTeacher;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getSpecializationTeacher() {
        return specializationTeacher;
    }

    public void setSpecializationTeacher(String specializationTeacher) {
        this.specializationTeacher = specializationTeacher;
    }

}