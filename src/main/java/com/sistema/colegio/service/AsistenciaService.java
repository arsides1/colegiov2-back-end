package com.sistema.colegio.service;




import com.sistema.colegio.model.Asistencia;

import java.util.List;

public interface AsistenciaService {
	 

	List<Asistencia> listarAsistencia();

	public abstract void insert(Asistencia asistencia);

	public abstract void update(Asistencia asistencia);

	public abstract void delete(Long asistenciaId);

	public abstract Asistencia findById(Long asistenciaId);

}
