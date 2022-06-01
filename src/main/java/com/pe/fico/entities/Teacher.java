package com.pe.fico.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTeacher;

    @NotEmpty(message = "Debe ingresar nombre del profesor")
    @Column(name = "nameTeacher", nullable = false, length = 150)
    private String nameTeacher;

    @NotEmpty(message = "Debe ingresar profesión del profesor")
    @Column(name = "specializationTeacher", nullable = false, length = 150)
    private String specializationTeacher;

    @ManyToOne
    @JoinColumn(name = "idFaculty", nullable = false)
    private Faculty faculty;

    public Teacher() {
        super();
    }

    public Teacher(int idTeacher, @NotEmpty(message = "Debe ingresar nombre del profesor") String nameTeacher,
            @NotEmpty(message = "Debe ingresar profesión del profesor") String specializationTeacher, Faculty faculty) {
        this.idTeacher = idTeacher;
        this.nameTeacher = nameTeacher;
        this.specializationTeacher = specializationTeacher;
        this.faculty = faculty;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


}