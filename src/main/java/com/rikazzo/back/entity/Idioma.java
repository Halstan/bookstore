package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "idiomas")
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIdioma;

    @Column(unique = true, length = 30)
    @NotBlank
    @Size(min = 5, max = 30)
    private String nombreIdioma;

}
