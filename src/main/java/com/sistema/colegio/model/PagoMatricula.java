package com.sistema.colegio.model;

import com.sistema.colegio.convertir.BooleanConverters;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
@Table(name = "pago_matricula")
public class PagoMatricula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column // no es necesario nombre de atributo de clase = nombre columna
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPago;
	
	@ManyToOne
	//fk
	@JoinColumn(name = "idMatricula",referencedColumnName = "idMatricula")
	private Matricula idMatricula;
	@Column(nullable = false)
	private Integer monto;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fechaRegistro;


	@Column()
	@Convert(converter= BooleanConverters.CharacterConverter.class )
	private Boolean estado;
}
