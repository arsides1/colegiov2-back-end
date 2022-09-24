package com.sistema.colegio.service;



import com.sistema.colegio.model.Apoderado;

import java.util.List;

public interface ApoderadoService {
	 

	List<Apoderado> listarApoderado();

	public abstract void insert(Apoderado apoderado);

	public abstract void update(Apoderado apoderado);

	public abstract void delete(Long apoderadoId);

	public abstract Apoderado findById(Long apoderadoId);

}
