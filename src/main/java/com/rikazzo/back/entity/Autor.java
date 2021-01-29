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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Audited
@Table(name = "autores")
public class Autor extends Auditoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAutor;

    @Column(length = 40, nullable = false)
    @NotBlank
    private String nombreAutor;

    @Column(length = 160, nullable = false)
    @NotBlank
    @Size(min = 30, max = 160)
    private String urlFoto;

    @Column(length = 40, nullable = false)
    @NotBlank
    private String apellido;

    @Column(length = 300)
    @Size(min = 20, max = 300)
    private String biografia;

    private LocalDate fechaNacimiento;

    @UpdateTimestamp
    private Date fechaModificacion;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Libro> libros;
}
