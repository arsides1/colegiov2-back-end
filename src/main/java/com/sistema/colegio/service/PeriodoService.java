package com.sistema.colegio.service;





import com.sistema.colegio.model.Periodo;

import java.util.List;
import java.util.Optional;

public interface PeriodoService {
	 

	List<Periodo> listarPeriodo();

	public abstract void insert(Periodo periodo);

	public Periodo actualizarPeriodo(Periodo periodo);

	Optional<Periodo> findByEstado(Boolean estado);

	public abstract void update(Periodo periodo);

	public abstract void delete(Long periodoId);

	public abstract Periodo findById(Long periodoId);

}
