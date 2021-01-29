package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface CategoriaRepository extends RevisionRepository<Categoria, Integer, Integer>, JpaRepository<Categoria, Integer> {
}
