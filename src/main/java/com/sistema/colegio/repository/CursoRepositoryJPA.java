package com.sistema.colegio.repository;

import com.sistema.colegio.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepositoryJPA extends JpaRepository<Curso, Long>  {
	

	
}
