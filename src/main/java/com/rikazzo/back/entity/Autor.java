package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "autor")
    @JsonIgnore
    private List<Libro> libros;

}
