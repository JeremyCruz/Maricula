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
import javax.validation.constraints.Positive;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCourse;

    @NotEmpty(message = "Debe ingresar nombre del curso")
    @Column(name = "nameCourse", nullable = false, length = 150)
    private String nameCourse;

    @NotEmpty(message = "Debe ingresar salon")
    @Column(name = "roomCourse", nullable = false, length = 150)
    private String roomCourse;

    @Positive
    @Column(name = "creditsCourse", nullable = false)
    private int creditsCourse;

    @Positive
    @Column(name = "maxStudentsCourse", nullable = false)
    private int maxStudentsCourse;

    @ManyToOne
    @JoinColumn(name = "idTeacher", nullable = false)
    private Teacher teacher;

    @Column(name = "available", nullable = false)
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "idCampus", nullable = false)
    private Campus campus;

    public Course() {
        super();
    }

    public Course(int idCourse, @NotEmpty(message = "Debe ingresar nombre del curso") String nameCourse,
            @NotEmpty(message = "Debe ingresar salon") String roomCourse,
            @Positive int creditsCourse,
            @Positive int maxStudentsCourse,
            Teacher teacher, Campus campus, Boolean avai) {
        super();
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.roomCourse = roomCourse;
        this.creditsCourse = creditsCourse;
        this.maxStudentsCourse = maxStudentsCourse;
        this.teacher = teacher;
        this.campus = campus;
        this.available = avai;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getRoomCourse() {
        return roomCourse;
    }

    public void setRoomCourse(String roomCourse) {
        this.roomCourse = roomCourse;
    }

    public int getCreditsCourse() {
        return creditsCourse;
    }

    public void setCreditsCourse(int creditsCourse) {
        this.creditsCourse = creditsCourse;
    }

    public int getMaxStudentsCourse() {
        return maxStudentsCourse;
    }

    public void setMaxStudentsCourse(int maxStudentsCourse) {
        this.maxStudentsCourse = maxStudentsCourse;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

}