package com.sistema.colegio.repository;

import com.sistema.colegio.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocenteRepositoryJPA extends JpaRepository<Docente, Long>  {
	

	
}
