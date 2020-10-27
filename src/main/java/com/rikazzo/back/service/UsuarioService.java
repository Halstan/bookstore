package com.rikazzo.back.service;

import com.rikazzo.back.entity.Usuario;
import com.rikazzo.back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    Usuario findByUsername(String username){
        return this.usuarioRepository.findUsuarioByUsername(username);
    }

    List<Usuario> findUsuarioByIdSexo(Integer idSexo){
        return this.usuarioRepository.findUsuariosBySexoIdSexo(idSexo);
    }

    List<Usuario> findByNombre(String nombre){
        return this.usuarioRepository.findUsuariosByNombre(nombre);
    }

    Usuario findById(Long idUsuario){
        return this.usuarioRepository.findById(idUsuario).orElse(null);
    }

    Usuario agregarUsuario(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    void eliminarUsuario(Long idUsuario){
        this.usuarioRepository.deleteById(idUsuario);
    }
}
