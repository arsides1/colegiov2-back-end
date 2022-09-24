package com.sistema.colegio.service.Impl;

import com.sistema.colegio.model.Matricula;
import com.sistema.colegio.repository.MatriculaRepositoryJPA;
import com.sistema.colegio.service.MatriculaService;
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
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepositoryJPA matriculaRepo;


	@Override
	public void insert(Matricula matricula) {
		LocalDate fechaActual = LocalDate.now();
		matricula.setFechaRegistro(fechaActual);
		 matriculaRepo.save(matricula);
		
	}
	@Override
	public void update(Matricula matricula) {
		LocalDate fechaActual = LocalDate.now();
		matricula.setFechaRegistro(fechaActual);
		matriculaRepo.save(matricula);
	}
	@Override
	public void delete(Long matriculaId) {
		 matriculaRepo.deleteById(matriculaId);
		
	}
	@Override
	public Matricula findById(Long matriculaId) {
		return matriculaRepo.findById(matriculaId).orElse(null);
	
	}


	@Override
	public List<Matricula> listarMatricula() {
		List<Matricula>matriculas = StreamSupport.stream(
				this.matriculaRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return matriculas;
	}
    


}
