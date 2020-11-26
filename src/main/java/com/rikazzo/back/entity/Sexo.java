package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sexos")
public class Sexo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short idSexo;

    @Column(length = 30, nullable = false)
    @NotBlank
    @Size(min = 3, max = 30)
    private String nombreSexo;

    @OneToMany(mappedBy = "sexo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Usuario> usuarios;

}
