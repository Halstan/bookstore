package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Table(name = "alquileres")
public class Alquiler extends Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlquiler;

    @Column(nullable = false)
    private LocalDate fechaRetorno;

    @UpdateTimestamp
    private LocalDate fechaCreacion;

    private Boolean estado;

    private Double precio;

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
