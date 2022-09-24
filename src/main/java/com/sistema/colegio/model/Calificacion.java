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
@Table(name = "calificacion")
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificacion;

    @ManyToOne
    //fk
    @JoinColumn(name = "idAlumno",referencedColumnName = "idAlumno")
    private Alumno idAlumno;

    @ManyToOne
    //fk
    @JoinColumn(name = "idCurricula",referencedColumnName = "idCurricula")
    private Curricula idCurricula;



    @Column (length = 2, nullable = false)
    private Integer nota1;

    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;
}
