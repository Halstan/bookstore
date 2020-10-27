package com.rikazzo.back.repository;

import com.rikazzo.back.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditorialRepository extends JpaRepository<Editorial, Integer> {

    /**
     * Busca las editoriales filtrandolas por el nombre de la editorial
     * @param nombreEditorial es el nombre de la editorial
     * */
    List<Editorial> findEditorialsByNombreEditorial(String nombreEditorial);

}
