package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends RevisionRepository<Libro, Long, Long>, JpaRepository<Libro, Long> {

    /**
     * Filtra los libros por el estado
     * @param estado es el estado de libros a filtrar
     * */
    List<Libro> findLibrosByEstado(Boolean estado);

    /**
     * Filtra los libros por el nombre del libro
     * @param nombreLibro es el nombre de libro a filtrar
     * */
    List<Libro> findLibrosByNombreLibroStartsWith(String nombreLibro);

    /**
     * Filtra los libros por el Id de la editorial
     * @param idEditorial es el id de la editorial
     * */
    List<Libro> findLibrosByEditorialIdEditorial(Integer idEditorial);

    /**
     * Filtra los libros por el nombre de la editorial
     * @param nombreEditorial es el nombre de la editorial
     * */
    List<Libro> findLibrosByEditorialNombreEditorialStartsWith(String nombreEditorial);

    /**
     * Filtra los libros por el id de la categoría
     * @param idCategoria es el id de la categoría provista
     * */
    List<Libro> findLibrosByCategoriaIdCategoria(Integer idCategoria);

    /**
     * Busca el libro por el ISBN proveido
     * @param isbn es el identificador único de cada libro
     * */
    Optional<Libro> findLibroByIsbn(String isbn);

    /**
     * Actualiza el estado del libro a false para indicar que ha sido prestado
     * @param idLibro es el id del libro provisto
     * */
    @Query(value = "update libros set estado = FALSE where id_libro = :idLibro", nativeQuery = true)
    @Modifying
    void setEstadoFalse(@Param("idLibro") Long idLibro);

    /**
     * Actualiza el estado del libro a true para indicar que está disponible para reservar
     * @param idLibro es el id del libro provisto
     * */
    @Query(value = "update libros set estado = TRUE where id_libro = :idLibro", nativeQuery = true)
    @Modifying
    void setEstadoTrue(@Param("idLibro") Long idLibro);

}
