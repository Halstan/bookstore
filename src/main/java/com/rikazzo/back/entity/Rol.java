package com.rikazzo.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRol;

    @Column(length = 40, unique = true)
    private String nombreRol;

}
