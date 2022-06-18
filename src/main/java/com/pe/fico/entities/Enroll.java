package com.pe.fico.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Enrollment")
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEnroll;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "idCourse", nullable = false)
    private Course course;

    public Enroll() {
    }

    public Enroll(int idEnroll, Users user, Course course) {
        this.idEnroll = idEnroll;
        this.user = user;
        this.course = course;
    }

    public int getIdEnroll() {
        return idEnroll;
    }

    public void setIdEnroll(int idEnroll) {
        this.idEnroll = idEnroll;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
