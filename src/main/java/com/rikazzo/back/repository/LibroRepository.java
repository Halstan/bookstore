package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    /**
     * Filtra los libros por el nombre del libro
     * @param nombreLibro es el nombre de libro a filtrar
     * */
    List<Libro> findLibrosByNombreLibro(String nombreLibro);

    /**
     * Filtra los libros por el Id de la editorial
     * @param idEditorial es el id de la editorial
     * */
    List<Libro> findLibrosByEditorialIdEditorial(Integer idEditorial);

    /**
     * Filtra los libros por el nombre de la editorial
     * @param nombreEditorial es el nombre de la editorial
     * */
    List<Libro> findLibrosByEditorialNombreEditorial(String nombreEditorial);

    /**
     * Filtra los libros por el id de la categoría
     * @param idCategoria es el id de la categoría provista
     * */
    List<Libro> findLibrosByCategoriaIdCategoria(Integer idCategoria);

}
