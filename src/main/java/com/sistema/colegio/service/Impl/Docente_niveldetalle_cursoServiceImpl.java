package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Docente_niveldetalle_curso;
import com.sistema.colegio.repository.Docente_niveldetalle_cursoRepositoryJPA;
import com.sistema.colegio.service.Docente_niveldetalle_cursoService;
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
public class Docente_niveldetalle_cursoServiceImpl implements Docente_niveldetalle_cursoService {


	private final Docente_niveldetalle_cursoRepositoryJPA docente_niveldetalle_cursoRepo;



	@Override
	public List<Docente_niveldetalle_curso> listarDocente_niveldetalle_curso() {
		List<Docente_niveldetalle_curso>docente_niveldetalle_curso = StreamSupport.stream(
				this.docente_niveldetalle_cursoRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return docente_niveldetalle_curso;
	}

	@Override
	public void insert(Docente_niveldetalle_curso docente_niveldetalle_curso) {
		LocalDate fechaActual = LocalDate.now();
		docente_niveldetalle_curso.setFechaRegistro(fechaActual);
		docente_niveldetalle_cursoRepo.save(docente_niveldetalle_curso);
	}

	@Override
	public void update(Docente_niveldetalle_curso docente_niveldetalle_curso) {
		Docente_niveldetalle_curso docente_niveldetalle_cursoActual = docente_niveldetalle_cursoRepo.findById(docente_niveldetalle_curso.getIdDocenteniveldetallecurso()).orElse(null);

		//valido si es distinto a nulo
		if (docente_niveldetalle_cursoActual !=null)
		{
			docente_niveldetalle_cursoActual.setIdNivelDetalleCurso(docente_niveldetalle_curso.getIdNivelDetalleCurso());
			docente_niveldetalle_cursoActual.setIdDocente(docente_niveldetalle_curso.getIdDocente());
			docente_niveldetalle_cursoActual.setEstado(docente_niveldetalle_curso.getEstado());
			docente_niveldetalle_cursoActual.setFechaRegistro(docente_niveldetalle_curso.getFechaRegistro());
			docente_niveldetalle_cursoActual = docente_niveldetalle_cursoRepo.save(docente_niveldetalle_cursoActual);
		}
		docente_niveldetalle_curso = docente_niveldetalle_cursoActual;
		LocalDate fechaActual = LocalDate.now();
		docente_niveldetalle_curso.setFechaRegistro(fechaActual);
		docente_niveldetalle_cursoRepo.save(docente_niveldetalle_curso);
	}

	@Override
	public void delete(Long docente_niveldetalle_cursoId) {
		docente_niveldetalle_cursoRepo.deleteById(docente_niveldetalle_cursoId);
	}

	@Override
	public Docente_niveldetalle_curso findById(Long docente_niveldetalle_cursoId) {
		return docente_niveldetalle_cursoRepo.findById(docente_niveldetalle_cursoId).orElse(null);
	}
}
