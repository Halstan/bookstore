package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Filtra al usuario por el nombre de usuario
     * @param username es el nombre de usuario provisto
     * */
    Usuario findUsuarioByUsername(String username);

    /**
     * Filtra los usuarios por el id del sexo
     * @param idSexo es el id del sexo provisto
     * */
    List<Usuario> findUsuariosBySexoIdSexo(Integer idSexo);

    /**
     * Filtra los usuarios por el nombre
     * @param nombre es el nombre del usuario
     * */
    List<Usuario> findUsuariosByNombre(String nombre);

}
