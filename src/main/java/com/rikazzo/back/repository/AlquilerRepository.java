package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {

    /**
     * Filtra los alquileres por el id del usuario
     * @param idUsuario Es el id del usuario
     * */
    List<Alquiler> findAlquilersByUsuarioIdUsuario(Long idUsuario);

    /**
     * Filtra los alquileres por el estado de estos
     * @param estado el es estado del libro puede ser true o false
     * */
    List<Alquiler> findAlquilersByEstado(boolean estado);

}
