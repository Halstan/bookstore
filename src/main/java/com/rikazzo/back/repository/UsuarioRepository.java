package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Filtra al usuario por el nombre de usuario
     * @param username es el nombre de usuario provisto
     * */
    Optional<Usuario> findUsuarioByUsername(String username);

    /**
     * Filtra los usuarios por el id del sexo
     * @param idSexo es el id del sexo provisto
     * */
    List<Usuario> findUsuariosBySexoIdSexo(Short idSexo);

    /**
     * Filtra los usuarios por el nombre
     * @param nombre es el nombre del usuario
     * */
    List<Usuario> findUsuariosByNombreStartsWith(String nombre);

}
