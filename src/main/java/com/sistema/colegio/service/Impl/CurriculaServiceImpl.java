package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Curricula;
import com.sistema.colegio.repository.CurriculaRepositoryJPA;
import com.sistema.colegio.service.CurriculaService;
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
public class CurriculaServiceImpl implements CurriculaService {


	private final CurriculaRepositoryJPA curriculaRepo;



	@Override
	public List<Curricula> listarCurricula() {
		List<Curricula>curricula = StreamSupport.stream(
				this.curriculaRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return curricula;
	}

	@Override
	public void insert(Curricula curricula) {
		LocalDate fechaActual = LocalDate.now();
		curricula.setFechaRegistro(fechaActual);
		curriculaRepo.save(curricula);
	}

	@Override
	public void update(Curricula curricula) {
		Curricula curriculaActual = curriculaRepo.findById(curricula.getIdCurricula()).orElse(null);

		//valido si es distinto a nulo
		if (curriculaActual !=null)
		{
			curriculaActual.setIdDocenteniveldetallecurso(curricula.getIdDocenteniveldetallecurso());
			curriculaActual.setDescripcion(curricula.getDescripcion());
			curriculaActual.setEstado(curricula.getEstado());
			curriculaActual.setFechaRegistro(curricula.getFechaRegistro());
			curriculaActual = curriculaRepo.save(curriculaActual);
		}
		curricula = curriculaActual;

		LocalDate fechaActual = LocalDate.now();
		curricula.setFechaRegistro(fechaActual);
		curriculaRepo.save(curricula);
	}

	@Override
	public void delete(Long curriculaId) {
		curriculaRepo.deleteById(curriculaId);
	}

	@Override
	public Curricula findById(Long curriculaId) {
		return curriculaRepo.findById(curriculaId).orElse(null);
	}
}
