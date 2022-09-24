package com.sistema.colegio.service;





import com.sistema.colegio.model.Docente_niveldetalle_curso;

import java.util.List;

public interface Docente_niveldetalle_cursoService {
	 

	List<Docente_niveldetalle_curso> listarDocente_niveldetalle_curso();

	public abstract void insert(Docente_niveldetalle_curso docente_niveldetalle_curso);

	public abstract void update(Docente_niveldetalle_curso docente_niveldetalle_curso);

	public abstract void delete(Long docente_niveldetalle_cursoId);

	public abstract Docente_niveldetalle_curso findById(Long docente_niveldetalle_cursoId);

}
