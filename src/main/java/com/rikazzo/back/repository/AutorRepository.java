package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;

public interface AutorRepository extends RevisionRepository<Autor, Integer, Integer>, JpaRepository<Autor, Integer> {

    /**
     * Filtra los autores por el nombre del autor
     * @param nombre es el nombre del autor a filtrar
     * */
    List<Autor> findAutorsByNombreAutorStartsWith(String nombre);

}
