package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Horario;
import com.sistema.colegio.repository.HorarioRepositoryJPA;
import com.sistema.colegio.service.HorarioService;
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
public class HorarioServiceImpl implements HorarioService {


	private final HorarioRepositoryJPA horarioRepo;



	@Override
	public List<Horario> listarHorario() {
		List<Horario>horario = StreamSupport.stream(
				this.horarioRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return horario;
	}

	@Override
	public void insert(Horario horario) {
		LocalDate fechaActual = LocalDate.now();
		horario.setFechaRegistro(fechaActual);
		horarioRepo.save(horario);
	}

	@Override
	public void update(Horario horario) {
		LocalDate fechaActual = LocalDate.now();
		horario.setFechaRegistro(fechaActual);
		horarioRepo.save(horario);
	}

	@Override
	public void delete(Long horarioId) {
		horarioRepo.deleteById(horarioId);
	}

	@Override
	public Horario findById(Long horarioId) {
		return horarioRepo.findById(horarioId).orElse(null);
	}
}
