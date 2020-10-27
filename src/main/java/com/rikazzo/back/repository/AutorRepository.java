package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    /**
     * Filtra los autores por el nombre del autor
     * @param nombre es el nombre del autor a filtrar
     * */
    List<Autor> findAutorsByNombreAutor(String nombre);

}
