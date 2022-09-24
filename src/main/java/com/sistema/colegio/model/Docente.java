package com.sistema.colegio.model;

import com.sistema.colegio.convertir.BooleanConverters;
import com.sistema.colegio.fecha_hora.JsonLocalDateDeserializer;
import com.sistema.colegio.fecha_hora.JsonLocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@Table(name = "docente")
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocente;

    @Column(length = 5, unique=true, nullable = false)
    private String codigo;

    @Column(length = 50, nullable = false)
    private String nombres;

    @Column(length = 50, nullable = false)
    private String apellidos;

    @Column(length = 8, unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    //@Temporal(TemporalType.DATE)
//    @JsonFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize( using = JsonLocalDateDeserializer.class )
    @JsonSerialize( using = JsonLocalDateSerializer.class )
    private LocalDate fechaNacimiento;

    @Column(length = 50, nullable = false)
    private String sexo;

    @Column(length = 100, nullable = false)
    private String gradoEstudio;

    @Column(length = 100, nullable = false)
    private String ciudad;
    @Column(length = 100, nullable = false)
    private String distrito;

    @Column(length = 100, nullable = false)
    private String direccion;

    @Column(length = 9, nullable=false)
    private String celular;


    @Column(length = 100, nullable = false)
    private String email;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "idDocente")
    @JsonBackReference(value="Docenteniveldetallecurso_matri")
    private List<Docente_niveldetalle_curso> DocenteniveldetallecursoList;


}
