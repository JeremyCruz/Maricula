package com.pe.fico.service;

import java.util.List;

import com.pe.fico.entities.Campus;


public interface ICampusService {

    public boolean save(Campus campus);

    public void delete(Integer idCampus);

    public List<Campus> findAll();

}
