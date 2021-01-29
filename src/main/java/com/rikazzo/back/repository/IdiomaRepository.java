package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface IdiomaRepository extends RevisionRepository<Idioma, Integer, Integer>, JpaRepository<Idioma, Integer> {
}
