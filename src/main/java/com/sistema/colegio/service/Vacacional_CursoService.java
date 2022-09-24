package com.sistema.colegio.service;





import com.sistema.colegio.model.Vacacional_Curso;

import java.util.List;

public interface Vacacional_CursoService {
	 

	List<Vacacional_Curso> listarVacacional_Curso();

	public abstract void insert(Vacacional_Curso vacacional_Curso);

	public abstract void update(Vacacional_Curso vacacional_Curso);

	public abstract void delete(Long vacacional_CursoId);

	public abstract Vacacional_Curso findById(Long vacacional_CursoId);

}
