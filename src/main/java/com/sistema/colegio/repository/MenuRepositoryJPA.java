package com.sistema.colegio.repository;



import com.sistema.colegio.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepositoryJPA extends JpaRepository<Menu, Long>  {
	

	
}
