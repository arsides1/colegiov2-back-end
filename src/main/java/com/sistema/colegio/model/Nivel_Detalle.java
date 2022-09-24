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
@Table(name = "nivel_detalle")
public class Nivel_Detalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNivelDetalle;

    @ManyToOne
    //fk
    @JoinColumn(name = "idNivel",referencedColumnName = "idNivel")
    private Nivel idNivel;


    @ManyToOne
    //fk
    @JoinColumn(name = "IdGradoSeccion",referencedColumnName = "IdGradoSeccion")
    private Grado_Seccion IdGradoSeccion;


    @Column(length = 2, nullable = false)
    private Integer totalVacantes;

    @Column(length = 2, nullable = false)
    private Integer totalVacantesDisponibles;

    @Column(length = 2, nullable = false)
    private Integer VacantesOcupadas;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idNivelDetalle")
    @JsonBackReference(value="Niveldetallecurso_NivelDetalle")
    private List<Nivel_detalle_Curso> niveldetallecursoList;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idNivelDetalle")
    @JsonBackReference(value="Matricula_NivelDetalle")
    private List<Matricula> matriculaList;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idNivelDetalle")
    @JsonBackReference(value="horaio_NivelDetalle")
    private List<Horario> horarioList;
}
