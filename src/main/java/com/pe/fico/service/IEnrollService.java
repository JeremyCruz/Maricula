package com.pe.fico.service;

import java.util.List;


import com.pe.fico.entities.Enroll;


public interface IEnrollService {
    
    public int verify(Enroll enroll);
    
    public boolean save(Enroll enroll);

    Enroll listId(int idEnroll);

    public void delete(int idEnroll);

    List<Enroll> findAll();

    public boolean update_available(Enroll enroll);

    List<Enroll> findByUser(String idEnroll);
}
