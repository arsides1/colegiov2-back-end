package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Grado_Seccion;
import com.sistema.colegio.repository.Grado_SeccionRepositoryJPA;
import com.sistema.colegio.service.Grado_SeccionService;
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
public class Grado_SeccionServiceImpl implements Grado_SeccionService {


	private final Grado_SeccionRepositoryJPA grado_SeccionRepo;



	@Override
	public List<Grado_Seccion> listarGrado_Seccion() {
		List<Grado_Seccion>grado_Seccion = StreamSupport.stream(
				this.grado_SeccionRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return grado_Seccion;
	}

	@Override
	public void insert(Grado_Seccion grado_Seccion) {
		LocalDate fechaActual = LocalDate.now();
		grado_Seccion.setFechaRegistro(fechaActual);
		grado_SeccionRepo.save(grado_Seccion);
	}

	@Override
	public void update(Grado_Seccion grado_Seccion) {
	Grado_Seccion grado_SeccionActual = grado_SeccionRepo.findById(grado_Seccion.getIdGradoSeccion()).orElse(null);

		//valido si es distinto a nulo
		if (grado_SeccionActual !=null)
		{
			grado_SeccionActual.setDescripcionGrado(grado_Seccion.getDescripcionGrado());
			grado_SeccionActual.setDescripcionSeccion(grado_Seccion.getDescripcionSeccion());
			grado_SeccionActual.setEstado(grado_Seccion.getEstado());
			grado_SeccionActual.setFechaRegistro(grado_Seccion.getFechaRegistro());
			grado_SeccionActual = grado_SeccionRepo.save(grado_SeccionActual);
		}
		grado_Seccion = grado_SeccionActual;
		LocalDate fechaActual = LocalDate.now();
		grado_Seccion.setFechaRegistro(fechaActual);
		grado_SeccionRepo.save(grado_Seccion);
	}

	@Override
	public Grado_Seccion actualizarGrado_Seccion(Grado_Seccion grado_Seccion) {
		LocalDate fechaActual = LocalDate.now();
		grado_Seccion.setFechaRegistro(fechaActual);
		//periodo.setEstado(false);
	/*	if( !periodo.getEstado()){
			periodo.setEstado(true);
		}
		if(null == periodo.getEstado()) {
			periodo.setEstado(false);
		}
*/
		return grado_SeccionRepo.save(grado_Seccion);
	}

	@Override
	public void delete(Long grado_SeccionId) {
		grado_SeccionRepo.deleteById(grado_SeccionId);
	}

	@Override
	public Grado_Seccion findById(Long grado_SeccionId) {
		return grado_SeccionRepo.findById(grado_SeccionId).orElse(null);
	}
}
