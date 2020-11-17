package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    @JoinColumn(name = "idCategoria", nullable = false)
    @NotNull
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "idEditorial", nullable = false)
    @NotNull
    private Editorial editorial;

    @Column(length = 80, nullable = false)
    @Size(min = 5, max = 80)
    @NotBlank
    private String nombreLibro;

    @Column(length = 200, nullable = false)
    @Size(max = 200)
    @NotBlank
    private String descripcion;

    @Column(length = 150, nullable = false)
    @Size(min = 30, max = 150)
    private String urlPortada;

    @Column(length = 30, unique = true)
    private String isbn;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @CreationTimestamp
    private LocalDate fechaCreacion;

    @UpdateTimestamp
    private LocalDate fechaActualizacion;

    private Double precio;

    private Boolean estado;

    @OneToMany(mappedBy = "libro")
    @JsonIgnore
    private List<Alquiler> alquileres;

    @ManyToOne
    @JoinColumn(name = "idIdioma", nullable = false)
    private Idioma idioma;

    @ManyToOne
    @JoinColumn(name = "idAutor", nullable = false)
    private Autor autor;

    @PrePersist
    void init(){
        estado = true;
    }
}
