package com.sistema.colegio.repository;


import com.sistema.colegio.model.PagoMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoMatriculaRepositoryJPA extends JpaRepository<PagoMatricula, Long>  {
	

	
}
