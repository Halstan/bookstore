package com.rikazzo.back.mapper;

import com.rikazzo.back.dto.AutorDTO;
import com.rikazzo.back.entity.Autor;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AutorMapper {

    Autor toAutor(AutorDTO autorDTO);

    List<AutorDTO> toAutorDTOs(List<Autor> autores);

    AutorDTO toAutorDTO(Autor autor);

}
