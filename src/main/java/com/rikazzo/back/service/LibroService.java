package com.rikazzo.back.service;

import com.rikazzo.back.entity.Libro;
import com.rikazzo.back.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    List<Libro> findAll(){
        return this.libroRepository.findAll();
    }

    List<Libro> findByNombre(String nombreLibro){
        return this.libroRepository.findLibrosByNombreLibro(nombreLibro);
    }

    List<Libro> findLibroByIdEditorial(Integer idEditorial){
        return this.libroRepository.findLibrosByEditorialIdEditorial(idEditorial);
    }

    List<Libro> findLibrosByNombreEditorial(String nombreEditorial){
        return this.libroRepository.findLibrosByEditorialNombreEditorial(nombreEditorial);
    }

    List<Libro> findLibrosByIdCategoria(Integer idCategoria){
        return this.libroRepository.findLibrosByCategoriaIdCategoria(idCategoria);
    }

    Optional<Libro> findById(Long idLibro){
        return this.libroRepository.findById(idLibro);
    }

    Libro agregarLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    Libro actualizarLibro(Libro libro){
        return this.libroRepository.save(libro);
    }

    void eliminarLibro(Long idLibro){
        this.libroRepository.deleteById(idLibro);
    }

}
