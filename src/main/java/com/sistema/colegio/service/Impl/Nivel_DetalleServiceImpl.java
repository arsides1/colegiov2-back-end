package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Nivel_Detalle;
import com.sistema.colegio.repository.Nivel_DetalleRepositoryJPA;
import com.sistema.colegio.service.Nivel_DetalleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class Nivel_DetalleServiceImpl implements Nivel_DetalleService {


	private final Nivel_DetalleRepositoryJPA nivel_DetalleRepo;



	@Override
	public List<Nivel_Detalle> listarNivel_Detalle() {
		List<Nivel_Detalle>nivel_Detalle = StreamSupport.stream(
				this.nivel_DetalleRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return nivel_Detalle;
	}

	@Override
	public void insert(Nivel_Detalle nivel_Detalle) {
		LocalDate fechaActual = LocalDate.now();
		nivel_Detalle.setFechaRegistro(fechaActual);
		nivel_DetalleRepo.save(nivel_Detalle);
	}

	@Override
	public void update(Nivel_Detalle nivel_Detalle) {
		LocalDate fechaActual = LocalDate.now();
		nivel_Detalle.setFechaRegistro(fechaActual);
		nivel_DetalleRepo.save(nivel_Detalle);
	}

	@Override
	public void delete(Long nivel_DetalleId) {
		nivel_DetalleRepo.deleteById(nivel_DetalleId);
	}

	@Override
	public Nivel_Detalle findById(Long nivel_DetalleId) {
		return nivel_DetalleRepo.findById(nivel_DetalleId).orElse(null);
	}
}
