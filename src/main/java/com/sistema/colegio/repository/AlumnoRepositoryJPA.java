package com.sistema.colegio.repository;


import com.sistema.colegio.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepositoryJPA extends JpaRepository<Alumno, Long>  {
	

	
}
