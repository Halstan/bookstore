package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends RevisionRepository<Usuario, Long, Long>, JpaRepository<Usuario, Long> {

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
     * Actualiza los datos del usuario por el id de este
     * @param idUsuario Es el id del usuario presente
     * */
    @Modifying(clearAutomatically = true)
    @Query(value="update usuarios set nombre= :nombre, apellido= :apellido, contrasenha= :contrasenha, " +
            "correo= :correo, sexo= :sexo, username= :username, fecha_modificacion = NOW() where id_usuario = :idUsuario", nativeQuery = true)
    void updateUsuario(@Param("nombre") String nombre, @Param("apellido") String apellido,
                       @Param("contrasenha") String contrasenha, @Param("correo") String correo,
                       @Param("sexo") String sexo, @Param("username") String username,
                       @Param("idUsuario") Long idUsuario);

    /**
     * Filtra los usuarios por el nombre
     * @param nombre es el nombre del usuario
     * */
    List<Usuario> findUsuariosByNombreStartsWith(String nombre);

    @Query(value = "select username from usuarios where usuarios.username = :username", nativeQuery = true)
    Optional<Usuario> getUsernameByUsernameProvided(String username);

}
