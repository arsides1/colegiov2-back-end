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
@Table(name = "vacional_curso")
public class Vacacional_Curso implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column // no es necesario nombre de atributo de clase = nombre columna
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVacionalCurso;

    @Column(nullable = false)
    private Integer notaCurso;

    @OneToOne(mappedBy = "vacional_curso" ) // mappedBy en la clase entidad que mapea la tabla donde no existe la FK
    private Alumno alumno; // Relacion agregacion

    @OneToOne(mappedBy = "vacional_curso" ) // mappedBy en la clase entidad que mapea la tabla donde no existe la FK
    private Curso curso; // Relacion agregacion

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;
}
