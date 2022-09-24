package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Curso;
import com.sistema.colegio.repository.CursoRepositoryJPA;
import com.sistema.colegio.service.CursoService;
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
public class CursoServiceImpl implements CursoService {


	private final CursoRepositoryJPA cursoRepo;



	@Override
	public List<Curso> listarCurso() {
		List<Curso>curso = StreamSupport.stream(
				this.cursoRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return curso;
	}

	@Override
	public void insert(Curso curso) {
		LocalDate fechaActual = LocalDate.now();
		curso.setFechaRegistro(fechaActual);
		cursoRepo.save(curso);
	}

	@Override
	public void update(Curso curso) {
		Curso cursoActual = cursoRepo.findById(curso.getIdCurso()).orElse(null);

		//valido si es distinto a nulo
		if (cursoActual !=null)
		{
			cursoActual.setDescripcion(curso.getDescripcion());
			cursoActual.setEstado(curso.getEstado());
			cursoActual.setFechaRegistro(curso.getFechaRegistro());
			cursoActual = cursoRepo.save(cursoActual);
		}
		curso = cursoActual;
		LocalDate fechaActual = LocalDate.now();
		curso.setFechaRegistro(fechaActual);
		cursoRepo.save(curso);
	}

	@Override
	public void delete(Long cursoId) {
		cursoRepo.deleteById(cursoId);
	}

	@Override
	public Curso findById(Long cursoId) {
		return cursoRepo.findById(cursoId).orElse(null);
	}
}
