package com.rikazzo.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Audited
@Table(name = "idiomas")
public class Idioma extends Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIdioma;

    @Column(unique = true, length = 30, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 5, max = 30)
    private String nombreIdioma;

    @OneToMany(mappedBy = "idioma", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Libro> libros;

}
