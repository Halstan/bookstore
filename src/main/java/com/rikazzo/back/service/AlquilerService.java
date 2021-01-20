package com.rikazzo.back.service;

import com.rikazzo.back.entity.Alquiler;
import com.rikazzo.back.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;

    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    @Transactional(readOnly = true)
    public List<Alquiler> findAll(){
        return this.alquilerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Alquiler> findAllPaginated(Pageable pageable){
        return this.alquilerRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Alquiler> findById(Long idAlquiler){
        return this.alquilerRepository.findById(idAlquiler);
    }

    @Transactional(readOnly = true)
    public List<Alquiler> findAlquilerByUsername(String username){
        return this.alquilerRepository.findAlquilersByUsuarioUsername(username);
    }

    @Transactional(readOnly = true)
    public List<Alquiler> findAlquilerByEstado(boolean estado){
        return this.alquilerRepository.findAlquilersByEstado(estado);
    }

    public Alquiler agregarAlquiler(Alquiler alquiler){
        Date fechaActual = new Date();
        if (alquiler.getLibro().getFechaVigencia().before(fechaActual)){
            alquiler.setPrecio(0.0);
        } else {
            alquiler.setPrecio(alquiler.getLibro().getPrecio());
        }
        return this.alquilerRepository.save(alquiler);
    }

    public Alquiler actualizarAlquiler(Alquiler alquiler){
        return this.alquilerRepository.save(alquiler);
    }

    public void setEstadoFalse(Long idAlquiler){
        this.alquilerRepository.setEstadoFalse(idAlquiler);
    }

    public void deleteAlquiler(Long idAlquiler){
        this.alquilerRepository.deleteById(idAlquiler);
    }
}
