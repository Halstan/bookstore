package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Filtra al usuario por el nombre de usuario
     * @param username es el nombre de usuario provisto
     * */
    Optional<Usuario> findUsuarioByUsername(String username);

    /**
     * Filtra los usuarios por el sexo
     * @param sexo es el sexo provisto
     * */
    List<Usuario> findUsuariosBySexo(String sexo);

    /**
     * Filtra los usuarios por el nombre
     * @param nombre es el nombre del usuario
     * */
    List<Usuario> findUsuariosByNombreStartsWith(String nombre);

    @Query(value = "select username from usuarios where usuarios.username = :username", nativeQuery = true)
    Optional<Usuario> getUsernameByUsernameProvided(String username);

}
