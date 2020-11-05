package com.rikazzo.back.service;

import com.rikazzo.back.entity.Alquiler;
import com.rikazzo.back.repository.AlquilerRepository;
import com.rikazzo.back.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlquilerService {

    private final AlquilerRepository alquilerRepository;
    private final LibroRepository libroRepository;

    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository, LibroRepository libroRepository) {
        this.alquilerRepository = alquilerRepository;
        this.libroRepository = libroRepository;
    }

    @Transactional(readOnly = true)
    public List<Alquiler> findAll(){
        return this.alquilerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Alquiler> findById(Long idAlquiler){
        return this.alquilerRepository.findById(idAlquiler);
    }

    @Transactional(readOnly = true)
    public List<Alquiler> findAlquilerByIdUsuario(Long idUsuario){
        return this.alquilerRepository.findAlquilersByUsuarioIdUsuario(idUsuario);
    }

    @Transactional(readOnly = true)
    public List<Alquiler> findAlquilerByEstado(boolean estado){
        return this.alquilerRepository.findAlquilersByEstado(estado);
    }

    public Alquiler agregarAlquiler(Alquiler alquiler){
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
