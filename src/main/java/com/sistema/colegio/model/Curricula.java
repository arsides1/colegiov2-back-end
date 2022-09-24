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
@Table(name = "curricula")
public class Curricula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurricula;

    @ManyToOne
    //fk
    @JoinColumn(name = "idDocenteniveldetallecurso",referencedColumnName = "idDocenteniveldetallecurso")
    private Docente_niveldetalle_curso idDocenteniveldetallecurso;

    @Column(length = 50, nullable = false)
    private String descripcion;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCurricula")
    @JsonBackReference(value = "curricula_calificacion")
    private List<Calificacion> calificacionList;



}
