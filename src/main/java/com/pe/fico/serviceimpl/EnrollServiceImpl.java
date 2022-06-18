package com.pe.fico.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.fico.entities.Enroll;
import com.pe.fico.repositories.IEnrollRepository;
import com.pe.fico.service.IEnrollService;

@Service
public class EnrollServiceImpl implements IEnrollService {

    @Autowired
    private IEnrollRepository eR;

    @Override
    public boolean save(Enroll enroll) {

        Enroll objEnroll = eR.save(enroll);

        if (objEnroll == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Enroll listId(int idEnroll) {
        Optional<Enroll> op = eR.findById(idEnroll);
        return op.isPresent() ? op.get() : new Enroll();
    }

    @Override
    public void delete(int idEnroll) {
        eR.deleteById(idEnroll);
    }

    @Override
    public List<Enroll> findAll() {
        return eR.findAll();
    }

    @Override
    public boolean update_available(Enroll enroll) {
        int aux = eR.alumnos_matriculados(enroll.getCourse().getIdCourse());
        if (aux +1 > enroll.getCourse().getMaxStudentsCourse()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int verify(Enroll enroll) {
        int rpta=eR.buscarUser(enroll.getUser().getId(),enroll.getCourse().getIdCourse());
		return rpta;
    }

    @Override
    public List<Enroll> findByUser(String idEnroll) {
        // TODO Auto-generated method stub
        return eR.CoursesByUser(idEnroll);
    }

}
