package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    Set<Rol> findRolsByNombreRol(String nombre);

}
