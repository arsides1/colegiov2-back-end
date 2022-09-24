package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Apoderado;
import com.sistema.colegio.repository.ApoderadoRepositoryJPA;
import com.sistema.colegio.service.ApoderadoService;
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
public class ApoderadoServiceImpl implements ApoderadoService {


	private final ApoderadoRepositoryJPA apoderadoRepo;



	@Override
	public List<Apoderado> listarApoderado() {
		List<Apoderado>apoderado = StreamSupport.stream(
				this.apoderadoRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return apoderado;
	}

	@Override
	public void insert(Apoderado apoderado) {
		LocalDate fechaActual = LocalDate.now();
		apoderado.setFechaRegistro(fechaActual);
		apoderadoRepo.save(apoderado);
	}

	@Override
	public void update(Apoderado apoderado) {
		Apoderado apoderadoActual = apoderadoRepo.findById(apoderado.getIdApoderado()).orElse(null);

		//valido si es distinto a nulo
		if (apoderadoActual !=null)
		{
			apoderadoActual.setTipoRelacion(apoderado.getTipoRelacion());
			apoderadoActual.setNombres(apoderado.getNombres());
			apoderadoActual.setApellidos(apoderado.getApellidos());
			apoderadoActual.setDni(apoderado.getDni());
			apoderadoActual.setFechaNacimiento(apoderado.getFechaNacimiento());
			apoderadoActual.setSexo(apoderado.getSexo());
			apoderadoActual.setEstadoCivil(apoderado.getEstadoCivil());
			apoderadoActual.setCiudad(apoderado.getCiudad());
			apoderadoActual.setDistrito(apoderado.getDistrito());
			apoderadoActual.setDireccion(apoderado.getDireccion());
			apoderadoActual.setCelular(apoderado.getCelular());
			apoderadoActual.setEstado(apoderado.getEstado());
			apoderadoActual.setFechaRegistro(apoderado.getFechaRegistro());
			apoderadoActual = apoderadoRepo.save(apoderadoActual);
		}
		apoderado = apoderadoActual;
		LocalDate fechaActual = LocalDate.now();
		apoderado.setFechaRegistro(fechaActual);
		apoderadoRepo.save(apoderado);
	}

	@Override
	public void delete(Long apoderadoId) {
		apoderadoRepo.deleteById(apoderadoId);
	}

	@Override
	public Apoderado findById(Long apoderadoId) {
		return apoderadoRepo.findById(apoderadoId).orElse(null);
	}
}
