package com.sistema.colegio.repository;

import com.sistema.colegio.model.Nivel_detalle_Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Nivel_detalle_CursoRepositoryJPA extends JpaRepository<Nivel_detalle_Curso, Long>  {
	

	
}
