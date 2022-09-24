package com.sistema.colegio.service;


import com.sistema.colegio.model.Nivel;

import java.util.List;

public interface NivelService {
	 

	List<Nivel> listarNivel();

	public abstract void insert(Nivel nivel);

	public Nivel actualizarNivel(Nivel nivel);

	public abstract void update(Nivel nivel);

	public abstract void delete(Long nivelId);

	public abstract Nivel findById(Long nivelId);

}
