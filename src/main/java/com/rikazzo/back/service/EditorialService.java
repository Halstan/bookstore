package com.rikazzo.back.service;

import com.rikazzo.back.entity.Editorial;
import com.rikazzo.back.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EditorialService {

    private final EditorialRepository editorialRepository;

    @Autowired
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    List<Editorial> findAll(){
        return this.editorialRepository.findAll();
    }

    List<Editorial> findByNombre(String nombre){
        return this.editorialRepository.findEditorialsByNombreEditorial(nombre);
    }

    Editorial findById(Integer idEditorial){
        return this.editorialRepository.findById(idEditorial).orElse(null);
    }

    Editorial agregarEditorial(Editorial editorial){
        return this.editorialRepository.save(editorial);
    }

    Editorial actualizarEditorial(Editorial editorial){
        return this.editorialRepository.save(editorial);
    }

    void eliminarEditorial(Integer idEditorial){
        this.editorialRepository.deleteById(idEditorial);
    }
}
