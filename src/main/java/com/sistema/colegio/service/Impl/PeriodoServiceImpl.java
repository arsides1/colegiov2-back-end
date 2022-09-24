package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Periodo;
import com.sistema.colegio.repository.PeriodoRepositoryJPA;
import com.sistema.colegio.service.PeriodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class PeriodoServiceImpl implements PeriodoService {


	private final PeriodoRepositoryJPA periodoRepo;



	@Override
	public List<Periodo> listarPeriodo() {
		List<Periodo>periodo = StreamSupport.stream(
				this.periodoRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return periodo;
	}

	@Override
	public void insert(Periodo periodo) {
		LocalDate fechaActual = LocalDate.now();
		periodo.setFechaRegistro(fechaActual);
	/*	if(null != periodo.getEstado()){
			periodo.setEstado(true);
		}
		if(null != periodo.getEstado()){
			periodo.setEstado(false);
		}
*/
		periodoRepo.save(periodo);
	}

	@Override
	public Periodo actualizarPeriodo(Periodo periodo) {
		LocalDate fechaActual = LocalDate.now();
		periodo.setFechaRegistro(fechaActual);
		//periodo.setEstado(false);
	/*	if( !periodo.getEstado()){
			periodo.setEstado(true);
		}
		if(null == periodo.getEstado()) {
			periodo.setEstado(false);
		}
*/
		return periodoRepo.save(periodo);
	}

	@Override
	public Optional<Periodo> findByEstado(Boolean estado) {
		return periodoRepo.findByEstado(estado);
	}


	@Override
	public void update(Periodo periodo) {

		/*Periodo periodoActual = periodoRepo.findById(periodo.getIdPeriodo()).orElse(null);

		//valido si es distinto a nulo
		if (periodoActual !=null)
		{
			periodoActual.setDescripcion(periodo.getDescripcion());
            periodoActual.setFechaInicio(periodo.getFechaInicio());
            periodoActual.setFechaFin(periodo.getFechaFin());
            periodoActual.setEstado(periodo.getEstado());
            periodoActual.setFechaRegistro(periodo.getFechaRegistro());
			periodoActual = periodoRepo.save(periodoActual);
		}
		periodo = periodoActual;*/
		LocalDate fechaActual = LocalDate.now();
		periodo.setFechaRegistro(fechaActual);
		//periodo.setEstado(false);
		periodoRepo.save(periodo);
	}

	@Override
	public void delete(Long periodoId) {
		periodoRepo.deleteById(periodoId);
	}

	@Override
	public Periodo findById(Long periodoId) {
		return periodoRepo.findById(periodoId).orElse(null);
	}
}
