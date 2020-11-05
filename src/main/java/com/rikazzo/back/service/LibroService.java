package com.rikazzo.back.service;

import com.rikazzo.back.entity.Libro;
import com.rikazzo.back.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Transactional(readOnly = true)
    public List<Libro> findAll(){
        return this.libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Libro> findByEstado(Boolean estado){
        return this.libroRepository.findLibrosByEstado(estado);
    }

    @Transactional(readOnly = true)
    public List<Libro> findByNombre(String nombreLibro){
        return this.libroRepository.findLibrosByNombreLibroStartsWith(nombreLibro);
    }

    @Transactional(readOnly = true)
    public List<Libro> findLibrosByIdEditorial(Integer idEditorial){
        return this.libroRepository.findLibrosByEditorialIdEditorial(idEditorial);
    }

    @Transactional(readOnly = true)
    public List<Libro> findLibrosByNombreEditorial(String nombreEditorial){
        return this.libroRepository.findLibrosByEditorialNombreEditorialStartsWith(nombreEditorial);
    }

    @Transactional(readOnly = true)
    public List<Libro> findLibrosByIdCategoria(Integer idCategoria){
        return this.libroRepository.findLibrosByCategoriaIdCategoria(idCategoria);
    }

    @Transactional(readOnly = true)
    public Optional<Libro> findById(Long idLibro){
        return this.libroRepository.findById(idLibro);
    }

    @Transactional(rollbackFor = Exception.class)
    public Libro agregarLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    @Transactional(rollbackFor = Exception.class)
    public Libro actualizarLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarLibro(Long idLibro){
        this.libroRepository.deleteById(idLibro);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setEstadoFale(Long idLibro){
        this.libroRepository.setEstadoFalse(idLibro);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setEstadoTrue(Long idLibro){
        this.libroRepository.setEstadoTrue(idLibro);
    }

}
