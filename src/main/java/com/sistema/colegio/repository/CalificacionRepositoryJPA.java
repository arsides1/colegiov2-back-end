package com.sistema.colegio.repository;



import com.sistema.colegio.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepositoryJPA extends JpaRepository<Calificacion, Long>  {
	

	
}
