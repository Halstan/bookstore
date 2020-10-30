package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {

    /**
     * Busca las editoriales filtrandolas por el nombre de la editorial
     * @param nombreEditorial es el nombre de la editorial
     * */
    List<Editorial> findEditorialsByNombreEditorial(String nombreEditorial);

    /**
     * Busca las editoriales filtrandolas por el estado de estas
     * @param estado es el estado de la editorial
     * */
    List<Editorial> findEditorialsByEstado(boolean estado);

    /**
     * Actualiza el estado de la editorial proveída a false dando a entender que ya no está en funcionamiento
     * @param idEditorial es el id de la editorial
     * */
    @Query(value = "update editoriales set estado = 0 where id_editorial = :idEditorial", nativeQuery = true)
    @Modifying
    void setEstadoFalse(@Param("idEditorial") Integer idEditorial);

}
