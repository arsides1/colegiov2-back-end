package com.sistema.colegio.service;




import com.sistema.colegio.model.Docente;

import java.util.List;

public interface DocenteService {
	 

	List<Docente> listarDocente();

	public abstract void insert(Docente docente);

	public abstract void update(Docente docente);

	public abstract void delete(Long docenteId);

	public abstract Docente findById(Long docenteId);

}
