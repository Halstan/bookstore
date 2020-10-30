package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "libros")
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
    @Size(min = 5, max = 80)
    @NotBlank
    private String nombreLibro;

    @Column(length = 200)
    private String descripcion;

    @Column(length = 30)
    private String isbn;

    @Column(nullable = false)
    private Date fechaPublicacion;

    @CreationTimestamp
    private Date fechaCreacion;

    @UpdateTimestamp
    private Date fechaActualizacion;

    private Boolean estado;

    @OneToMany(mappedBy = "libro")
    private List<Alquiler> alquileres;

    @ManyToMany
    @JoinTable(
            name = "LibroIdioma",
            joinColumns = {@JoinColumn(name = "idLibro")},
            inverseJoinColumns = {@JoinColumn(name = "idIdioma")})
    private Set<Idioma> idiomas;

    @PrePersist
    void init(){
        estado = true;
    }
}
