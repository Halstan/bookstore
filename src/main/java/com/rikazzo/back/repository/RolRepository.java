package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Set;

public interface RolRepository extends RevisionRepository<Rol, Integer, Integer>, JpaRepository<Rol, Integer> {

    Set<Rol> findRolsByNombreRol(String nombre);

}
