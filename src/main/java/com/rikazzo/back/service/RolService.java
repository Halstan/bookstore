package com.rikazzo.back.service;

import com.rikazzo.back.entity.Rol;
import com.rikazzo.back.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Transactional(readOnly = true)
    List<Rol> findAll(){
        return this.rolRepository.findAll();
    }

    @Transactional(readOnly = true)
    Set<Rol> findByNombre(String nombre){
        return this.rolRepository.findRolsByNombreRol(nombre);
    }

    @Transactional(readOnly = true)
    Rol findById(Integer idRol){
        return this.rolRepository.findById(idRol).orElse(null);
    }

    @Transactional(rollbackFor = Exception.class)
    void eliminarRol(Integer idRol){
        this.rolRepository.deleteById(idRol);
    }
}
