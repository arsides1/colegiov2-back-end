package com.sistema.colegio.service;





import com.sistema.colegio.model.Nivel_detalle_Curso;

import java.util.List;

public interface Nivel_detalle_CursoService {
	 

	List<Nivel_detalle_Curso> listarNivel_detalle_Curso();

	public abstract void insert(Nivel_detalle_Curso nivel_detalle_Curso);

	public abstract void update(Nivel_detalle_Curso nivel_detalle_Curso);

	public abstract void delete(Long nivel_detalle_CursoId);

	public abstract Nivel_detalle_Curso findById(Long nivel_detalle_CursoId);

}
