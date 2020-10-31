package com.rikazzo.back.service;

import com.rikazzo.back.entity.Libro;
import com.rikazzo.back.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> findAll(){
        return this.libroRepository.findAll();
    }

    public List<Libro> findByEstado(Boolean estado){
        return this.libroRepository.findLibrosByEstado(estado);
    }

    public List<Libro> findByNombre(String nombreLibro){
        return this.libroRepository.findLibrosByNombreLibroStartsWith(nombreLibro);
    }

    public List<Libro> findLibrosByIdEditorial(Integer idEditorial){
        return this.libroRepository.findLibrosByEditorialIdEditorial(idEditorial);
    }

    public List<Libro> findLibrosByNombreEditorial(String nombreEditorial){
        return this.libroRepository.findLibrosByEditorialNombreEditorialStartsWith(nombreEditorial);
    }

    public List<Libro> findLibrosByIdCategoria(Integer idCategoria){
        return this.libroRepository.findLibrosByCategoriaIdCategoria(idCategoria);
    }

    public Optional<Libro> findById(Long idLibro){
        return this.libroRepository.findById(idLibro);
    }

    public Libro agregarLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    public Libro actualizarLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    public void eliminarLibro(Long idLibro){
        this.libroRepository.deleteById(idLibro);
    }

}
