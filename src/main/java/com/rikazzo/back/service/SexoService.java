package com.rikazzo.back.service;

import com.rikazzo.back.entity.Sexo;
import com.rikazzo.back.repository.SexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SexoService {

    private final SexoRepository sexoRepository;

    @Autowired
    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    List<Sexo> findAll(){
        return this.sexoRepository.findAll();
    }

    Optional<Sexo> findById(Integer idSexo){
        return this.sexoRepository.findById(idSexo);
    }

    Sexo agregarSexo(Sexo sexo){
        return this.sexoRepository.save(sexo);
    }

    void eliminarSexo(Integer idSexo){
        this.sexoRepository.deleteById(idSexo);
    }
}
