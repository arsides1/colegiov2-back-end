package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Calificacion;
import com.sistema.colegio.repository.CalificacionRepositoryJPA;
import com.sistema.colegio.service.CalificacionService;
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
public class CalificacionServiceImpl implements CalificacionService {


	private final CalificacionRepositoryJPA calificacionRepo;



	@Override
	public List<Calificacion> listarCalificacion() {
		List<Calificacion>calificacion = StreamSupport.stream(
				this.calificacionRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return calificacion;
	}

	@Override
	public void insert(Calificacion calificacion) {
		LocalDate fechaActual = LocalDate.now();
		calificacion.setFechaRegistro(fechaActual);
		calificacionRepo.save(calificacion);
	}

	@Override
	public void update(Calificacion calificacion) {
		Calificacion calificacionActual = calificacionRepo.findById(calificacion.getIdCalificacion()).orElse(null);

		//valido si es distinto a nulo
		if (calificacionActual !=null)
		{
			calificacionActual.setIdAlumno(calificacion.getIdAlumno());
			calificacionActual.setIdCurricula(calificacion.getIdCurricula());
			calificacionActual.setNota1(calificacion.getNota1());
			calificacionActual.setEstado(calificacion.getEstado());
			calificacionActual.setFechaRegistro(calificacion.getFechaRegistro());
			calificacionActual = calificacionRepo.save(calificacionActual);
		}
		calificacion = calificacionActual;
		LocalDate fechaActual = LocalDate.now();
		calificacion.setFechaRegistro(fechaActual);
		calificacionRepo.save(calificacion);
	}

	@Override
	public void delete(Long calificacionId) {
		calificacionRepo.deleteById(calificacionId);
	}

	@Override
	public Calificacion findById(Long calificacionId) {
		return calificacionRepo.findById(calificacionId).orElse(null);
	}
}
