package com.rikazzo.back.mapper;

import com.rikazzo.back.dto.LibroDTO;
import com.rikazzo.back.entity.Libro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface LibroMapper {

    Libro toLibro(LibroDTO libroDTO);

    List<LibroDTO> toLibroDtos(List<Libro> libros);

    LibroDTO toLibroDTO(Libro libro);

}
