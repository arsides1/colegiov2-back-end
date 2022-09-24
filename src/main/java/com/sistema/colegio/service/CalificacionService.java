package com.sistema.colegio.service;



import com.sistema.colegio.model.Calificacion;

import java.util.List;

public interface CalificacionService {
	 

	List<Calificacion> listarCalificacion();

	public abstract void insert(Calificacion calificacion);

	public abstract void update(Calificacion calificacion);

	public abstract void delete(Long calificacionId);

	public abstract Calificacion findById(Long calificacionId);

}
