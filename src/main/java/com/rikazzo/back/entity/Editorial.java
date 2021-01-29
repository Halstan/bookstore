package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Audited
@Table(name = "editoriales")
public class Editorial extends Auditoria implements Serializable {

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
    @Column(updatable = false)
    private LocalDate fechaCreacion;

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
