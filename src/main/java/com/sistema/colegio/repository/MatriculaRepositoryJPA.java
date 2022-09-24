package com.sistema.colegio.repository;


import com.sistema.colegio.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepositoryJPA extends JpaRepository<Matricula, Long>  {
	

	
}
