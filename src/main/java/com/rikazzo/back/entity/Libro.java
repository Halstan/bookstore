package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@Table(name = "libros")
public class Libro extends Auditoria implements Serializable {

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

    @Column(name = "urlPortada", length = 150, nullable = false)
    @Size(min = 30, max = 150)
    private String portada;

    @Column(length = 30, unique = true)
    private String isbn;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate fechaCreacion;

    @UpdateTimestamp
    private LocalDate fechaActualizacion;

    private Double precio;

    private Date fechaVigencia;

    private Boolean estado;

    @OneToMany(mappedBy = "libro", fetch = FetchType.LAZY)
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

    @PreUpdate
    void update(){
        estado = true;
    }

}
