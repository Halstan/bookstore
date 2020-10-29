package com.rikazzo.back.service;

import com.rikazzo.back.entity.Editorial;
import com.rikazzo.back.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EditorialService {

    private final EditorialRepository editorialRepository;

    @Autowired
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    public List<Editorial> findAll(){
        return this.editorialRepository.findAll();
    }

    public List<Editorial> findByNombre(String nombre){
        return this.editorialRepository.findEditorialsByNombreEditorial(nombre);
    }

    public Optional<Editorial> findById(Integer idEditorial){
        return this.editorialRepository.findById(idEditorial);
    }

    public Editorial agregarEditorial(Editorial editorial){
        return this.editorialRepository.save(editorial);
    }

    public Editorial actualizarEditorial(Editorial editorial){
        return this.editorialRepository.save(editorial);
    }

    public void eliminarEditorial(Integer idEditorial){
        this.editorialRepository.deleteById(idEditorial);
    }
}
