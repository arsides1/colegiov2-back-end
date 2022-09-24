package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Vacacional_Curso;
import com.sistema.colegio.repository.Vacacional_CursoRepositoryJPA;
import com.sistema.colegio.service.Vacacional_CursoService;
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
public class Vacacional_CursoServiceImpl implements Vacacional_CursoService {


	private final Vacacional_CursoRepositoryJPA vacacional_CursoRepo;



	@Override
	public List<Vacacional_Curso> listarVacacional_Curso() {
		List<Vacacional_Curso>vacacional_Curso = StreamSupport.stream(
				this.vacacional_CursoRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return vacacional_Curso;
	}

	@Override
	public void insert(Vacacional_Curso vacacional_Curso) {
		LocalDate fechaActual = LocalDate.now();
		vacacional_Curso.setFechaRegistro(fechaActual);
		vacacional_CursoRepo.save(vacacional_Curso);
	}

	@Override
	public void update(Vacacional_Curso vacacional_Curso) {
		LocalDate fechaActual = LocalDate.now();
		vacacional_Curso.setFechaRegistro(fechaActual);
		vacacional_CursoRepo.save(vacacional_Curso);
	}

	@Override
	public void delete(Long vacacional_CursoId) {
		vacacional_CursoRepo.deleteById(vacacional_CursoId);
	}

	@Override
	public Vacacional_Curso findById(Long vacacional_CursoId) {
		return vacacional_CursoRepo.findById(vacacional_CursoId).orElse(null);
	}
}
