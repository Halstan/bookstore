package com.rikazzo.back.service;

import com.rikazzo.back.entity.Alquiler;
import com.rikazzo.back.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;

    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    public List<Alquiler> findAll(){
        return this.alquilerRepository.findAll();
    }

    public Optional<Alquiler> findById(Long idAlquiler){
        return this.alquilerRepository.findById(idAlquiler);
    }

    public List<Alquiler> findAlquilerByIdUsuario(Long idUsuario){
        return this.alquilerRepository.findAlquilersByUsuarioIdUsuario(idUsuario);
    }

    public List<Alquiler> findAlquilerByEstado(boolean estado){
        return this.alquilerRepository.findAlquilersByEstado(estado);
    }

    public Alquiler agregarAlquiler(Alquiler alquiler){
        return this.alquilerRepository.save(alquiler);
    }

    public Alquiler actualizarAlquiler(Alquiler alquiler){
        return this.alquilerRepository.save(alquiler);
    }

    public void deleteAlquiler(Long idAlquiler){
        this.alquilerRepository.deleteById(idAlquiler);
    }
}
