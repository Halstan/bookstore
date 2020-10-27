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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Editorial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEditorial;

    @Column(length = 50)
    private String nombreEditorial;

    @Column(length = 50, nullable = false)
    @NotBlank
    private String fundador;

    @NotBlank
    private Date fechaFundacion;

    @CreationTimestamp
    private Date fechaCreacion;

    @OneToMany(mappedBy = "editorial")
    @JsonIgnore
    private List<Libro> libros;

    @OneToMany(mappedBy = "editorial")
    @JsonIgnore
    private List<Autor> autors;

}
