package com.sistema.colegio.service;





import com.sistema.colegio.model.Curso;

import java.util.List;

public interface CursoService {
	 

	List<Curso> listarCurso();

	public abstract void insert(Curso curso);

	public abstract void update(Curso curso);

	public abstract void delete(Long cursoId);

	public abstract Curso findById(Long cursoId);

}
