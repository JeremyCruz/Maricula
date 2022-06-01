package com.pe.fico.repositories;
import com.pe.fico.entities.Campus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ICampusRepository extends JpaRepository<Campus, Integer> {
	
}