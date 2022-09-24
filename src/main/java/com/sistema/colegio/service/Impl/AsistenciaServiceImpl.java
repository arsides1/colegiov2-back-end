package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Asistencia;
import com.sistema.colegio.repository.AsistenciaRepositoryJPA;
import com.sistema.colegio.service.AsistenciaService;
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
public class AsistenciaServiceImpl implements AsistenciaService {


	private final AsistenciaRepositoryJPA asistenciaRepo;



	@Override
	public List<Asistencia> listarAsistencia() {
		List<Asistencia>asistencia = StreamSupport.stream(
				this.asistenciaRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return asistencia;
	}

	@Override
	public void insert(Asistencia asistencia) {
		LocalDate fechaActual = LocalDate.now();
		asistencia.setFechaIngreso(fechaActual);
		asistenciaRepo.save(asistencia);
	}

	@Override
	public void update(Asistencia asistencia) {
		Asistencia asistenciaActual = asistenciaRepo.findById(asistencia.getIdAsistencia()).orElse(null);

		//valido si es distinto a nulo
		if (asistenciaActual !=null)
		{
			asistenciaActual.setFechaIngreso(asistencia.getFechaIngreso());
			asistenciaActual.setIngresoConfirmado(asistencia.getIngresoConfirmado());
			asistenciaActual.setIdAlumno(asistencia.getIdAlumno());
			asistenciaActual = asistenciaRepo.save(asistenciaActual);
		}
		asistencia = asistenciaActual;
		LocalDate fechaActual = LocalDate.now();
		asistencia.setFechaIngreso(fechaActual);
		asistenciaRepo.save(asistencia);
	}

	@Override
	public void delete(Long asistenciaId) {
		asistenciaRepo.deleteById(asistenciaId);
	}

	@Override
	public Asistencia findById(Long asistenciaId) {
		return asistenciaRepo.findById(asistenciaId).orElse(null);
	}
}
