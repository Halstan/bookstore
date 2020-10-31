package com.rikazzo.back.service;

import com.rikazzo.back.entity.Usuario;
import com.rikazzo.back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    public Optional<Usuario> findByUsername(String username){
        return this.usuarioRepository.findUsuarioByUsername(username);
    }

    public List<Usuario> findUsuarioByIdSexo(Integer idSexo){
        return this.usuarioRepository.findUsuariosBySexoIdSexo(idSexo);
    }

    public List<Usuario> findByNombre(String nombre){
        return this.usuarioRepository.findUsuariosByNombre(nombre);
    }

    public Optional<Usuario> findById(Long idUsuario){
        return this.usuarioRepository.findById(idUsuario);
    }

    public Usuario agregarUsuario(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long idUsuario){
        this.usuarioRepository.deleteById(idUsuario);
    }
}
