package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;

    @Column(length = 40, unique = true)
    @NotBlank
    private String nombreCategoria;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private List<Libro> libros;

}
