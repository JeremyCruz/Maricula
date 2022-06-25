package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.Course;

public interface ICourseService {

    public boolean save(Course course);

    Course listId(int idCourse);

    public void delete(int idCourse);

    List<Course> findAll();

    List<Course> findAvailableCourses();

    public void updateCheck(int idCourse);
    public void updateCheckA(int idCourse);
}
