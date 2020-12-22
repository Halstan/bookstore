package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    /**
     * Filtra los alquileres por el nombre de usuario del usuario
     * @param username Es el nombre de usuario del usuario
     * */
    List<Alquiler> findAlquilersByUsuarioUsername(String username);

    /**
     * Filtra los alquileres por el estado de estos
     * @param estado el es estado del libro puede ser true o false
     * */
    List<Alquiler> findAlquilersByEstado(boolean estado);

    /**
     * Actualiza el estado del alquiler a false para indicar que ha sido cancelado
     * @param idAlquiler es el id del libro provisto
     * */
    @Query(value = "update alquileres set estado = FALSE where id_alquiler = :idAlquiler", nativeQuery = true)
    @Modifying
    @Transactional
    void setEstadoFalse(@Param("idAlquiler") Long idAlquiler);

}
