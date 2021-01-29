package com.rikazzo.back.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class LibroDTO {

    private Long idLibro;

    private CategoriaDTO categoria;

    private EditorialDTO editorial;

    private String nombreLibro;

    private String descripcion;

    private String portada;

    private String isbn;

    private LocalDate fechaPublicacion;

    private LocalDate fechaCreacion;

    private LocalDate fechaActualizacion;

    private Double precio;

    private Date fechaVigencia;

    private Boolean estado;

    private IdiomaDTO idioma;

    private AutorDTO autor;

}
