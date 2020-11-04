package com.rikazzo.back.service;

import com.rikazzo.back.entity.Idioma;
import com.rikazzo.back.repository.IdiomaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class IdiomaService {

    private final IdiomaRepository idiomaRepository;

    @Autowired
    public IdiomaService(IdiomaRepository idiomaRepository) {
        this.idiomaRepository = idiomaRepository;
    }

    @Transactional(readOnly = true)
    public List<Idioma> findAll(){
        return this.idiomaRepository.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public Idioma agregarIdioma(Idioma idioma){
        return this.idiomaRepository.save(idioma);
    }

    @Transactional(rollbackFor = Exception.class)
    public Idioma actualizarIdioma(Idioma idioma){
        return this.idiomaRepository.save(idioma);
    }

    @Transactional(readOnly = true)
    public Optional<Idioma> findById(Integer idIdioma){
        return this.idiomaRepository.findById(idIdioma);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarIdioma(Integer idIdioma){
        this.idiomaRepository.deleteById(idIdioma);
    }
}
