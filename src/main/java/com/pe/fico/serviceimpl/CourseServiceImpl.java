package com.pe.fico.serviceimpl;
import java.util.List;
import java.util.Optional;

import com.pe.fico.entities.Course;
import com.pe.fico.repositories.ICourseRepository;
import com.pe.fico.service.ICourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseRepository cR;

    @Override
    public boolean save(Course course) {
        Course objCourse = cR.save(course);
        if (objCourse == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Course listId(int idCourse) {
        Optional<Course> op = cR.findById(idCourse);
		return op.isPresent() ? op.get() : new Course();
    }

    @Override
    public void delete(int idCourse) {
        cR.deleteById(idCourse);

    }

    @Override
    public List<Course> findAll() {
        return cR.findAll();
    }

    @Override
    public List<Course> findAvailableCourses() {
        return cR.findCoursesAvailables();
    }

    @Override
    public void updateCheck(int idCourse) {
       cR.update_available(idCourse);
    }

    @Override
    public void updateCheckA(int idCourse) {
        // TODO Auto-generated method stub
        cR.update_false(idCourse);
    }

}
