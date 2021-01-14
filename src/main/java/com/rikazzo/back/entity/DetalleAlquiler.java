package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detallesAlquileres")
public class DetalleAlquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleAlquiler;

    private Double total;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAlquiler")
    @JsonIgnore
    private Alquiler alquiler;

}
