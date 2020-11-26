package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(length = 50, nullable = false)
    @Size(min = 5, max = 50)
    @NotBlank
    private String nombreEditorial;

    @Column(length = 50, nullable = false)
    @Size(min = 5, max = 50)
    @NotBlank
    private String fundador;

    @Column(nullable = false)
    private LocalDate fechaFundacion;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @NotNull
    private boolean estado;

    @OneToMany(mappedBy = "editorial", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Libro> libros;

    @PrePersist
    void init(){
        estado = true;
    }

}
