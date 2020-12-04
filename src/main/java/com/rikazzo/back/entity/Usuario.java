package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @Size(min = 5, max = 40)
    private String nombre;

    @Column(length = 60)
    @Size(min = 10, max = 60)
    private String apellido;

    @Column(length = 40, unique = true, nullable = false)
    @NotBlank
    @Size(min = 8, max = 40)
    private String username;

    @Column(length = 70, unique = true)
    @Size(min = 10, max = 70)
    private String correo;

    @Column(length = 100, nullable = false)
    @NotBlank
    @Size(min = 8, max = 20)
    private String contrasenha;

    @Transient
    @Size(min = 8, max = 20)
    @NotBlank
    private String asegurarContrasenha;

    @UpdateTimestamp
    private Date fechaModificacion;

    private boolean activado;

    @Column(length = 15, nullable = false)
    private String sexo;

    @JsonIgnore
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
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
