package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Nivel_detalle_Curso;
import com.sistema.colegio.repository.Nivel_detalle_CursoRepositoryJPA;
import com.sistema.colegio.service.Nivel_detalle_CursoService;
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
public class Nivel_detalle_CursoServiceImpl implements Nivel_detalle_CursoService {


	private final Nivel_detalle_CursoRepositoryJPA nivel_detalle_CursoRepo;



	@Override
	public List<Nivel_detalle_Curso> listarNivel_detalle_Curso() {
		List<Nivel_detalle_Curso>nivel_detalle_Curso = StreamSupport.stream(
				this.nivel_detalle_CursoRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return nivel_detalle_Curso;
	}

	@Override
	public void insert(Nivel_detalle_Curso nivel_detalle_Curso) {
		LocalDate fechaActual = LocalDate.now();
		nivel_detalle_Curso.setFechaRegistro(fechaActual);
		nivel_detalle_CursoRepo.save(nivel_detalle_Curso);
	}

	@Override
	public void update(Nivel_detalle_Curso nivel_detalle_Curso) {
		LocalDate fechaActual = LocalDate.now();
		nivel_detalle_Curso.setFechaRegistro(fechaActual);
		nivel_detalle_CursoRepo.save(nivel_detalle_Curso);
	}

	@Override
	public void delete(Long nivel_detalle_CursoId) {
		nivel_detalle_CursoRepo.deleteById(nivel_detalle_CursoId);
	}

	@Override
	public Nivel_detalle_Curso findById(Long nivel_detalle_CursoId) {
		return nivel_detalle_CursoRepo.findById(nivel_detalle_CursoId).orElse(null);
	}
}
