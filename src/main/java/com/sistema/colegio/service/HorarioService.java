package com.sistema.colegio.service;





import com.sistema.colegio.model.Horario;

import java.util.List;

public interface HorarioService {
	 

	List<Horario> listarHorario();

	public abstract void insert(Horario horario);

	public abstract void update(Horario horario);

	public abstract void delete(Long horarioId);

	public abstract Horario findById(Long horarioId);

}
