package com.sistema.colegio.service;




import com.sistema.colegio.model.Curricula;

import java.util.List;

public interface CurriculaService {
	 

	List<Curricula> listarCurricula();

	public abstract void insert(Curricula curricula);

	public abstract void update(Curricula curricula);

	public abstract void delete(Long curriculaId);

	public abstract Curricula findById(Long curriculaId);

}
