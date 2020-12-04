package com.rikazzo.back.service;

import com.rikazzo.back.entity.Rol;
import com.rikazzo.back.entity.Usuario;
import com.rikazzo.back.repository.RolRepository;
import com.rikazzo.back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UsuarioService {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(RolRepository rolRepository, UsuarioRepository usuarioRepository) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll(){
        return this.usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> findByUsername(String username){
        return this.usuarioRepository.findUsuarioByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<Usuario> findUsuarioByIdSexo(String sexo){
        return this.usuarioRepository.findUsuariosBySexo(sexo);
    }

    @Transactional(readOnly = true)
    public List<Usuario> findByNombre(String nombre){
        return this.usuarioRepository.findUsuariosByNombreStartsWith(nombre);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long idUsuario){
        return this.usuarioRepository.findById(idUsuario);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> getUsername(String username){
        return this.usuarioRepository.getUsernameByUsernameProvided(username);
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario agregarUsuario(Usuario usuario){
        Set<Rol> roles = this.rolRepository.findRolsByNombreRol("ROLE_USER");
        usuario.setRoles(roles);
        return this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarUsuario(Long idUsuario){
        this.usuarioRepository.deleteById(idUsuario);
    }
}
