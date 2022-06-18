package com.pe.fico.repositories;

import com.pe.fico.entities.Course;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Integer> {

    @Transactional
    @Modifying
    @Query(value = "update Course c set available = FALSE where c.id_course =?1", nativeQuery = true)
    void update_available(Integer curso);

    @Query("select c from Course c where c.available =TRUE")
	List<Course> findCoursesAvailables();
}