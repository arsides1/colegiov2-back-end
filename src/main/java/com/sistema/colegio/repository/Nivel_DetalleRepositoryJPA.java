package com.sistema.colegio.repository;


import com.sistema.colegio.model.Nivel_Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Nivel_DetalleRepositoryJPA extends JpaRepository<Nivel_Detalle, Long>  {
	

	
}
