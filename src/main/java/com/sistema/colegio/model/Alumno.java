package com.sistema.colegio.model;

import com.sistema.colegio.convertir.BooleanConverters;
import com.sistema.colegio.fecha_hora.JsonLocalDateDeserializer;
import com.sistema.colegio.fecha_hora.JsonLocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
@Table(name = "alumno")
public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column // no es necesario nombre de atributo de clase = nombre columna
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAlumno;

	@Column(length = 5, unique=true, nullable = false)
	private String codigo;

	@Column(length = 50, nullable = false)
	private String nombres;

	@Column(length = 50, nullable = false)
	private String apellidos;

	@Column(length = 8, unique = true, nullable = false)
	private String dni;

	@Column(nullable = false)
//	@Temporal(TemporalType.DATE)
//	@JsonFormat(pattern = "dd-MM-yyyy")
	@JsonDeserialize( using = JsonLocalDateDeserializer.class )
	@JsonSerialize( using = JsonLocalDateSerializer.class )
	private Date fechaNacimiento;

	@Column(length = 50, nullable = false)
	private String sexo;

	@Column(length = 100, nullable = false)
	private String ciudad;

	@Column(length = 100, nullable = false)
	private String distrito;

	@Column(length = 100, nullable = false)
	private String direccion;

	@Column(length = 100, nullable = false)
	private String nombreColegio;


	@Column()
	@Convert(converter= BooleanConverters.CharacterConverter.class )
	private Boolean estado;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaRegistro;




	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
	@JsonBackReference(value = "alumno_matricula")
	private List<Matricula> matriculaList;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
	@JsonBackReference(value = "alumno_calificacion")
	private List<Calificacion> calificacionList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idAlumno")
	@JsonBackReference(value = "alumno_asistencia")
	private List<Asistencia> asistenciaList;

	@OneToOne
	//Si necesito mapear la FK , clase entidad unida a la tabla debil!!!.
	@JoinColumn(name = "idVacionalCurso",nullable = false,unique = true,
			foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(idVacionalCurso) references vacional_curso(idVacionalCurso)"))
	@JsonBackReference //cortoBucle
	private Vacacional_Curso vacional_curso; // composicion.


}
