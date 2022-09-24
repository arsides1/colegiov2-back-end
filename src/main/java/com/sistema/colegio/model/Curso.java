package com.sistema.colegio.model;

import com.sistema.colegio.convertir.BooleanConverters;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
@Table(name = "curso")
public class Curso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column // no es necesario nombre de atributo de clase = nombre columna
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCurso;
	
	@Column( length = 50, nullable = false)
	private String descripcion;


	@Column()
	@Convert(converter= BooleanConverters.CharacterConverter.class )
	private Boolean estado;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaRegistro;


	@OneToMany(cascade = CascadeType.ALL,mappedBy = "idCurso")
	@JsonBackReference(value="Curso_NivelDetalleCurso")
	private List<Nivel_detalle_Curso> nivelDetalleCursoList;

	/*@OneToMany(cascade = CascadeType.ALL,mappedBy = "idcur")
	@JsonBackReference(value="cur_not")
	private List<Nota> notaList;
	*/
	//@JsonIgnore
	//@JsonBackReference
	/*@ManyToMany(mappedBy = "cursoList")
	private List<Profesor> profesorList;
	
	
	@ManyToMany(mappedBy = "cursoList")
	private List<Grado_Seccion> gradoList;*/

	@OneToOne
	//Si necesito mapear la FK , clase entidad unida a la tabla debil!!!.
	@JoinColumn(name = "idVacionalCurso",nullable = false,unique = true,
			foreignKey = @ForeignKey(foreignKeyDefinition = "foreign key(idVacionalCurso) references vacional_curso(idVacionalCurso)"))
	@JsonBackReference //cortoBucle
	private Vacacional_Curso vacional_curso; // composicion.
}
