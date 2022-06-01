package com.pe.fico.serviceimpl;

import java.util.List;

import com.pe.fico.entities.Faculty;
import com.pe.fico.repositories.IFacultyRepository;
import com.pe.fico.service.IFacultyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FacultyServiceImpl implements IFacultyService {

    @Autowired
    private IFacultyRepository fR;

    @Override
    public boolean save(Faculty faculty) {
        // TODO Auto-generated method stub
        Faculty objFac = fR.save(faculty);
        if (objFac == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void delete(Integer idFaculty) {
        // TODO Auto-generated method stub
        fR.deleteById(idFaculty);

    }

    @Override
    public List<Faculty> findAll() {
        // TODO Auto-generated method stub
        return fR.findAll();
    }

}