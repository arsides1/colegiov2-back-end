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
@Table(name = "docente_niveldetalle_curso")
public class Docente_niveldetalle_curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocenteniveldetallecurso;


    @ManyToOne
    //fk
    @JoinColumn(name = "idNivelDetalleCurso",referencedColumnName = "idNivelDetalleCurso")
    private Nivel_detalle_Curso idNivelDetalleCurso;

    @ManyToOne
    //fk
    @JoinColumn(name = "idDocente",referencedColumnName = "idDocente")
    private Docente idDocente;


    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;



}
