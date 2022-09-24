package com.sistema.colegio.repository;




import com.sistema.colegio.model.Curricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculaRepositoryJPA extends JpaRepository<Curricula, Long>  {
	

	
}
