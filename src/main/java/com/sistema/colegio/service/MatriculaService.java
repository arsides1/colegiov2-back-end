package com.sistema.colegio.service;



import com.sistema.colegio.model.Matricula;

import java.util.List;


public interface MatriculaService {
	 


	public abstract void insert(Matricula matricula);

	public abstract void update(Matricula matricula);

	public abstract void delete(Long matriculaId);

	public abstract Matricula findById(Long matriculaId);
	
	 List<Matricula> listarMatricula();


}
