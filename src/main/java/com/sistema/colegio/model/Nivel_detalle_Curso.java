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
@Table(name = "nivel_detalle_Curso")
public class Nivel_detalle_Curso implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column // no es necesario nombre de atributo de clase = nombre columna
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNivelDetalleCurso;

    @ManyToOne
    //fk
    @JoinColumn(name = "idNivelDetalle",referencedColumnName = "idNivelDetalle")
    private Nivel_Detalle idNivelDetalle;

    @ManyToOne
    //fk
    @JoinColumn(name = "idCurso",referencedColumnName = "idCurso")
    private Curso idCurso;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idNivelDetalleCurso")
    @JsonBackReference(value="Docenteniveldetallecurso_NivelDetalleCurso")
    private List<Docente_niveldetalle_curso> docenteniveldetallecursoList;
}
