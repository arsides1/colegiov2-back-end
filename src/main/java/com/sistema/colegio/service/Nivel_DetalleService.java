package com.sistema.colegio.service;





import com.sistema.colegio.model.Nivel_Detalle;

import java.util.List;

public interface Nivel_DetalleService {
	 

	List<Nivel_Detalle> listarNivel_Detalle();

	public abstract void insert(Nivel_Detalle nivel_Detalle);

	public abstract void update(Nivel_Detalle nivel_Detalle);

	public abstract void delete(Long nivel_DetalleId);

	public abstract Nivel_Detalle findById(Long nivel_DetalleId);

}
