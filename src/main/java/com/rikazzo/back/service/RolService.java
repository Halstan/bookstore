package com.rikazzo.back.service;

import com.rikazzo.back.entity.Rol;
import com.rikazzo.back.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RolService {

    private final RolRepository rolRepository;

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    List<Rol> findAll(){
        return this.rolRepository.findAll();
    }

    Rol findById(Integer idRol){
        return this.rolRepository.findById(idRol).orElse(null);
    }

    Rol agregarRol(Rol rol){
        return this.rolRepository.save(rol);
    }

    void eliminarRol(Integer idRol){
        this.rolRepository.deleteById(idRol);
    }
}
