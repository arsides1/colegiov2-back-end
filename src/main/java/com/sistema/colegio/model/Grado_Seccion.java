package com.sistema.colegio.model;

import com.sistema.colegio.convertir.BooleanConverters;
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
@Table(name = "grado_seccion")
public class Grado_Seccion implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdGradoSeccion")
    private Long idGradoSeccion;

    @Column(length = 50, nullable = false)
    private String descripcionGrado;

    @Column(length = 100, nullable = false)
    private String descripcionSeccion;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;


    @OneToMany(mappedBy = "IdGradoSeccion", cascade = CascadeType.ALL)
    private List<Nivel_Detalle> niveldetalleList;
}
