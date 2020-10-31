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
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(length = 40, nullable = false)
    @NotBlank
    private String nombre;

    @Column(length = 60)
    private String apellido;

    @Column(length = 40, unique = true)
    private String username;

    @Column(length = 100, nullable = false)
    @NotBlank
    private String contrasenha;

    @Transient
    private String asegurarContrasenha;

    @CreationTimestamp
    private Date fechaCreacion;

    private boolean activado;

    @ManyToOne
    @JoinColumn(name = "idSexo")
    private Sexo sexo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private List<Alquiler> alquileres;

    @ManyToMany
    @JoinTable(
            name = "UsuarioRol",
            joinColumns = {@JoinColumn(name = "idUsuario")},
            inverseJoinColumns = {@JoinColumn(name = "idRol")})
    private Set<Rol> roles;

    @PrePersist
    void init() {
        activado = true;
    }
}
