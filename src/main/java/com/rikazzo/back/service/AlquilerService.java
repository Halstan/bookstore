package com.rikazzo.back.service;

import com.rikazzo.back.entity.Alquiler;
import com.rikazzo.back.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;

    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    List<Alquiler> findAll(){
        return this.alquilerRepository.findAll();
    }

    Alquiler findById(Long idAlquiler){
        return this.alquilerRepository.findById(idAlquiler).orElse(null);
    }

    List<Alquiler> findAlquilerByIdUsuario(Long idUsuario){
        return this.alquilerRepository.findAlquilersByUsuarioIdUsuario(idUsuario);
    }

    List<Alquiler> findAlquilerByEstado(boolean estado){
        return this.alquilerRepository.findAlquilersByEstado(estado);
    }

    Alquiler agregarAlquiler(Alquiler alquiler){
        return this.alquilerRepository.save(alquiler);
    }

    Alquiler actualizarAlquilar(Alquiler alquiler){
        return this.alquilerRepository.save(alquiler);
    }

    void deleteAlquiler(Long idAlquiler){
        this.alquilerRepository.deleteById(idAlquiler);
    }
}
