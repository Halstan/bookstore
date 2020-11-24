package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alquileres")
public class Alquiler implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlquiler;

    @Column(nullable = false)
    private LocalDate fechaRetorno;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    @NotNull
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idLibro", nullable = false)
    @NotNull
    private Libro libro;

    @PrePersist
    void init(){
        estado = true;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "idAlquiler=" + idAlquiler +
                ", fechaRetorno=" + fechaRetorno +
                ", fechaCreacion=" + fechaCreacion +
                ", estado=" + estado +
                '}';
    }
}
