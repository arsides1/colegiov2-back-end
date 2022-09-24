package com.sistema.colegio.model;

import com.sistema.colegio.convertir.BooleanConverters;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of={"id"})
@ToString
@Table(name = "horario")
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    @Column(length = 50, nullable = false)
    private String diaSemana;

    @Column(nullable = false)
    private LocalTime horaInicio;

    @Column(nullable = false)
    private LocalTime horaFin;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;

    @ManyToOne
    //fk
    @JoinColumn(name = "idNivelDetalle",referencedColumnName = "idNivelDetalle", unique=true)
    private Nivel_Detalle idNivelDetalle;




}
