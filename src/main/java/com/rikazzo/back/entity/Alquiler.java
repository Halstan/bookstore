package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alquileres")
public class Alquiler implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlquiler;

    @Column(nullable = false)
    @NotBlank
    private Date fechaRetorno;

    @Column(nullable = false)
    private Date fechaCreacion;

    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idLibro")
    private Libro libro;

}
