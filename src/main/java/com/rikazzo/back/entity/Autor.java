package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "autores")
public class Autor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    @Column(length = 40, nullable = false)
    @NotBlank
    private String nombreAutor;

    @Column(length = 40, nullable = false)
    @NotBlank
    private String apellido;

    private Date fechaNacimiento;

    @CreationTimestamp
    private Date fechaCreacion;

    @ManyToOne
    @JoinColumn(name = "idEditorial")
    private Editorial editorial;

}
