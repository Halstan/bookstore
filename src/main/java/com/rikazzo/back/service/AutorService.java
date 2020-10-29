package com.rikazzo.back.service;

import com.rikazzo.back.entity.Autor;
import com.rikazzo.back.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> findAll(){
        return this.autorRepository.findAll();
    }

    public Optional<Autor> findById(Integer idAutor){
        return this.autorRepository.findById(idAutor);
    }

    public List<Autor> findByNombre(String nombreAutor){
        return this.autorRepository.findAutorsByNombreAutorStartsWith(nombreAutor);
    }

    public Autor agregarAutor(Autor autor){
        return this.autorRepository.save(autor);
    }

    public void eliminarAutor(Integer idAutor){
        this.autorRepository.deleteById(idAutor);
    }
}
