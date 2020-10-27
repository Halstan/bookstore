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

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(length = 40, nullable = false)
    @NotBlank
    private String nombre;

    @Column(length = 60)
    private String apellidoUsuario;

    @Column(length = 40, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    @NotBlank
    private String contrasenha;

    @Transient
    private String asegurarContrasenha;

    @CreationTimestamp
    private Date createdAt;

    private boolean activado;

    @ManyToOne
    @JoinColumn(name = "idSexo")
    private Sexo sexo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Alquiler> alquileres;
}
