package com.pe.fico.serviceimpl;
import java.util.List;
import java.util.Optional;

import com.pe.fico.entities.Teacher;
import com.pe.fico.repositories.ITeacherRepository;
import com.pe.fico.service.ITeacherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherRepository tR;

    @Override
    public boolean save(Teacher teacher) {
        // TODO Auto-generated method stub
        Teacher objTeacher = tR.save(teacher);
        if (objTeacher == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher listId(int idTeacher) {
        // TODO Auto-generated method stub
        Optional<Teacher> op = tR.findById(idTeacher);
        return op.isPresent() ? op.get() : new Teacher();
    }

    @Override
    public void delete(int idTeacher) {
        // TODO Auto-generated method stub
        tR.deleteById(idTeacher);

    }

    @Override
    public List<Teacher> list() {
        // TODO Auto-generated method stub
        return tR.findAll();
    }

}
