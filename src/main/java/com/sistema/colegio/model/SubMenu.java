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
@Table(name = "submenu")
public class SubMenu implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubMenu;


    @ManyToOne
    //fk
    @JoinColumn(name = "idMenu",referencedColumnName = "idMenu")
    private Menu  idMenu;


    @Column(length = 50, nullable = false)
    private String nombre;

    @Column()
    @Convert(converter= BooleanConverters.CharacterConverter.class )
    private Boolean estado;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaRegistro;

 /*   @OneToMany(mappedBy = "idSubMenu", cascade = CascadeType.ALL)
    @JsonBackReference(value="menu_permiso")
    private List<Permiso> permisoList;*/

}
