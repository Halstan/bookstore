package com.rikazzo.back.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EditorialDTO {

    private Integer idEditorial;

    private String nombreEditorial;

    private String fundador;

    private LocalDate fechaFundacion;

    private LocalDate fechaCreacion;

    private boolean estado;

}
