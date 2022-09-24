package com.sistema.colegio.service.Impl;


import com.sistema.colegio.model.Alumno;
import com.sistema.colegio.repository.AlumnoRepositoryJPA;
import com.sistema.colegio.service.AlumnoService;
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
public class AlumnoServiceImpl implements AlumnoService {


	private final AlumnoRepositoryJPA alumnoRepo;



	@Override
	public List<Alumno> listarAlumno() {
		List<Alumno>alumno = StreamSupport.stream(
				this.alumnoRepo.findAll().spliterator(),false).collect(Collectors.toList());
		return alumno;
	}

	@Override
	public void insert(Alumno alumno) {
		LocalDate fechaActual = LocalDate.now();
		alumno.setFechaRegistro(fechaActual);
		alumnoRepo.save(alumno);
	}

	@Override
	public void update(Alumno alumno) {
		Alumno alumnoActual = alumnoRepo.findById(alumno.getIdAlumno()).orElse(null);

		//valido si es distinto a nulo
		if (alumnoActual !=null)
		{
			alumnoActual.setCodigo(alumno.getCodigo());
			alumnoActual.setNombres(alumno.getNombres());
			alumnoActual.setApellidos(alumno.getApellidos());
			alumnoActual.setDni(alumno.getDni());
			alumnoActual.setFechaNacimiento(alumno.getFechaNacimiento());
			alumnoActual.setSexo(alumno.getSexo());
			alumnoActual.setCiudad(alumno.getCiudad());
			alumnoActual.setDistrito(alumno.getDistrito());
			alumnoActual.setDireccion(alumno.getDireccion());
			alumnoActual.setNombreColegio(alumno.getNombreColegio());
			alumnoActual.setEstado(alumno.getEstado());
			alumnoActual.setFechaRegistro(alumno.getFechaRegistro());
			alumnoActual = alumnoRepo.save(alumnoActual);
		}
		alumno = alumnoActual;
		LocalDate fechaActual = LocalDate.now();
		alumno.setFechaRegistro(fechaActual);
		alumnoRepo.save(alumno);
	}

	@Override
	public void delete(Long alumnoId) {
		alumnoRepo.deleteById(alumnoId);
	}

	@Override
	public Alumno findById(Long alumnoId) {
		return alumnoRepo.findById(alumnoId).orElse(null);
	}
}
