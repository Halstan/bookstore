package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "editoriales")
public class Editorial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEditorial;

    @Column(length = 50)
    private String nombreEditorial;

    @Column(length = 50, nullable = false)
    @NotBlank
    private String fundador;

    @Column(nullable = false)
    private Date fechaFundacion;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @NotNull
    private boolean estado;

    @OneToMany(mappedBy = "editorial")
    @JsonIgnore
    private List<Libro> libros;

    @OneToMany(mappedBy = "editorial")
    @JsonIgnore
    private List<Autor> autors;

    @PrePersist
    void init(){
        estado = true;
    }

}
