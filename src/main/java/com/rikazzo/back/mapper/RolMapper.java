package com.rikazzo.back.mapper;

import com.rikazzo.back.dto.RolDTO;
import com.rikazzo.back.entity.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RolMapper {

    Rol toRol(RolDTO rolDTO);

    List<RolDTO> toRolDtos(List<Rol> roles);

    RolDTO toRolDTO(Rol rol);

}
