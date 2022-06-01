package com.pe.fico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idStudent;

    @NotEmpty(message = "Debe ingresar nombre")
    @Column(name = "nameStudent", nullable = false, length = 150)
    private String nameStudent;

    @NotEmpty(message = "Debe ingresar email")
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @NotEmpty(message = "Debe ingresar carrera")
    @Column(name = "profession", nullable = false, length = 150)
    private String profession;

    public Student(int idStudent, @NotEmpty(message = "Debe ingresar nombre") String nameStudent,
            @NotEmpty(message = "Debe ingresar email") String email,
            @NotEmpty(message = "Debe ingresar carrera") String profession) {
        super();
        this.idStudent = idStudent;
        this.nameStudent = nameStudent;
        this.email = email;
        this.profession = profession;
    }

    public Student() {
        super();
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNameStudent() {
        return nameStudent;
    }

    public void setNameStudent(String nameStudent) {
        this.nameStudent = nameStudent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
