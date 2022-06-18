package com.pe.fico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pe.fico.entities.Enroll;

@Repository
public interface IEnrollRepository extends JpaRepository<Enroll, Integer> {

    @Query(value = "SELECT COUNT(e.id_course) FROM enrollment e where e.id_course =?1", 
    nativeQuery = true)
	public int alumnos_matriculados(Integer curso);

    @Query(value="SELECT count(u.id_user) FROM enrollment u where u.id_user =?1 AND u.id_course=?2", nativeQuery = true)
	public int buscarUser(Long user,Integer curso);

    @Query(value="SELECT  e.id_enroll, e.id_user, e.id_course FROM enrollment e INNER JOIN Users u ON e.id_user=u.id WHERE u.username=?1 GROUP BY  e.id_enroll, e.id_user, e.id_course", nativeQuery = true)
	List<Enroll> CoursesByUser(String user);

}
