package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Audited
@Table(name = "usuarios")
public class Usuario extends Auditoria implements Serializable {

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
    @Size(min = 8, max = 90)
    private String contrasenha;

    @Transient
    @Size(min = 8, max = 90)
    private String asegurarContrasenha;

    @UpdateTimestamp
    private Date fechaModificacion;

    private Boolean activado;

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

    @PreUpdate
    void update() {
        activado = true;
    }
}
