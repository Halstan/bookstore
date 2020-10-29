package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @ManyToOne
    @NotBlank
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;

    @ManyToOne
    @NotBlank
    @JoinColumn(name = "idEditorial")
    private Editorial editorial;

    @Column(length = 80, nullable = false)
    @NotBlank
    private String nombreLibro;

    @Column(nullable = false)
    @NotBlank
    private Integer cantidad;

    @Column(nullable = false)
    private Date fechaPublicacion;

    @CreationTimestamp
    private Date fechaCreacion;

    @UpdateTimestamp
    private Date fechaActualizacion;

    private Boolean estado;

    @OneToMany(mappedBy = "libro")
    private List<Alquiler> alquileres;

}
