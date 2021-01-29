package com.rikazzo.back.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class AutorDTO {

    private Integer idAutor;

    private String nombreAutor;

    private String urlFoto;

    private String apellido;

    private String biografia;

    private LocalDate fechaNacimiento;

    private Date fechaModificacion;

}
