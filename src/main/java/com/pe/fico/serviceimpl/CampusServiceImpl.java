package com.pe.fico.serviceimpl;

import java.util.List;

import com.pe.fico.entities.Campus;
import com.pe.fico.repositories.ICampusRepository;
import com.pe.fico.service.ICampusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CampusServiceImpl implements ICampusService {

    @Autowired
    private ICampusRepository cR;

    @Override
    public boolean save(Campus campus) {
        // TODO Auto-generated method stub
        Campus obCampus = cR.save(campus);
        if (obCampus == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void delete(Integer idCampus) {
        // TODO Auto-generated method stub
        cR.deleteById(idCampus);

    }

    @Override
    public List<Campus> findAll() {
        // TODO Auto-generated method stub
        return cR.findAll();
    }

}
