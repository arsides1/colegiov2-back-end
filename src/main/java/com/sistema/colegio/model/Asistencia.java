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
@Table(name = "asistencia")
public class Asistencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column // no es necesario nombre de atributo de clase = nombre columna
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistencia;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaIngreso;

    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean ingresoConfirmado;

    @ManyToOne
    //fk
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno idAlumno;

}
