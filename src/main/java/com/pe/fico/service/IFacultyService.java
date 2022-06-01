package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.Faculty;

public interface IFacultyService {
    public boolean save(Faculty faculty);

    public void delete(Integer idFaculty);

    public List<Faculty> findAll();
}
