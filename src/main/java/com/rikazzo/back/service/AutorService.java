package com.rikazzo.back.service;

import com.rikazzo.back.entity.Autor;
import com.rikazzo.back.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll(){
        return this.autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Autor> findById(Integer idAutor){
        return this.autorRepository.findById(idAutor);
    }

    @Transactional(readOnly = true)
    public List<Autor> findByNombre(String nombreAutor){
        return this.autorRepository.findAutorsByNombreAutorStartsWith(nombreAutor);
    }

    public Autor agregarAutor(Autor autor){
        return this.autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public Revisions<Integer, Autor> findRevisions(Integer idAutor){
        return this.autorRepository.findRevisions(idAutor);
    }

    public Autor actualizarAutor(Autor autor){
        return this.autorRepository.save(autor);
    }

    public void eliminarAutor(Integer idAutor){
        this.autorRepository.deleteById(idAutor);
    }
}
