package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Docente;
import com.sistema.colegio.repository.DocenteRepositoryJPA;
import com.sistema.colegio.service.DocenteService;
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
public class DocenteServiceImpl implements DocenteService {


	private final DocenteRepositoryJPA docenteRepo;



	@Override
	public List<Docente> listarDocente() {
		List<Docente>docente = StreamSupport.stream(
				this.docenteRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return docente;
	}

	@Override
	public void insert(Docente docente) {
		Docente docenteActual = docenteRepo.findById(docente.getIdDocente()).orElse(null);

		//valido si es distinto a nulo
		if (docenteActual !=null)
		{
			docenteActual.setCodigo(docente.getCodigo());
			docenteActual.setNombres(docente.getNombres());
			docenteActual.setApellidos(docente.getApellidos());
			docenteActual.setDni(docente.getDni());
			docenteActual.setFechaNacimiento(docente.getFechaNacimiento());
			docenteActual.setSexo(docente.getSexo());
			docenteActual.setGradoEstudio(docente.getGradoEstudio());
			docenteActual.setCiudad(docente.getCiudad());
			docenteActual.setDistrito(docente.getDistrito());
			docenteActual.setDireccion(docente.getDireccion());
			docenteActual.setEmail(docente.getEmail());
			docenteActual.setEstado(docente.getEstado());
			docenteActual.setFechaRegistro(docente.getFechaRegistro());
			docenteActual = docenteRepo.save(docenteActual);
		}
		docente = docenteActual;
		LocalDate fechaActual = LocalDate.now();
		docente.setFechaRegistro(fechaActual);
		docenteRepo.save(docente);
	}

	@Override
	public void update(Docente docente) {
		LocalDate fechaActual = LocalDate.now();
		docente.setFechaRegistro(fechaActual);
		docenteRepo.save(docente);
	}

	@Override
	public void delete(Long docenteId) {
		docenteRepo.deleteById(docenteId);
	}

	@Override
	public Docente findById(Long docenteId) {
		return docenteRepo.findById(docenteId).orElse(null);
	}
}
