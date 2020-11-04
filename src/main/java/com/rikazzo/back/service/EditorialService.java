package com.rikazzo.back.service;

import com.rikazzo.back.entity.Editorial;
import com.rikazzo.back.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {

    private final EditorialRepository editorialRepository;

    @Autowired
    public EditorialService(EditorialRepository editorialRepository) {
        this.editorialRepository = editorialRepository;
    }

    @Transactional(readOnly = true)
    public List<Editorial> findAll(){
        return this.editorialRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Editorial> findByEstado(Boolean estado){
        return this.editorialRepository.findEditorialsByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<Editorial> findByNombre(String nombre){
        return this.editorialRepository.findEditorialsByNombreEditorial(nombre);
    }

    @Transactional(readOnly = true)
    public Optional<Editorial> findById(Integer idEditorial){
        return this.editorialRepository.findById(idEditorial);
    }

    @Transactional(rollbackFor = Exception.class)
    public Editorial agregarEditorial(Editorial editorial){
        return this.editorialRepository.save(editorial);
    }

    @Transactional(rollbackFor = Exception.class)
    public Editorial actualizarEditorial(Editorial editorial){
        return this.editorialRepository.save(editorial);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarEditorial(Integer idEditorial){
        this.editorialRepository.deleteById(idEditorial);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setEstadoFalse(Integer idEditorial){
        this.editorialRepository.setEstadoFalse(idEditorial);
    }
}
