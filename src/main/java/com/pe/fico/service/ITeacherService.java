package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.Teacher;

public interface ITeacherService {

    public boolean save(Teacher teacher);

    Teacher listId(int idTeacher);

    public void delete(int idTeacher);

    List<Teacher> list();
}
