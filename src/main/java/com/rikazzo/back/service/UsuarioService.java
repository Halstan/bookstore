package com.rikazzo.back.service;

import com.rikazzo.back.entity.Rol;
import com.rikazzo.back.entity.Usuario;
import com.rikazzo.back.repository.RolRepository;
import com.rikazzo.back.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

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

    public Page<Usuario> findAllPaginated(Pageable pageable){
        return this.usuarioRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> findByUsername(String username){
        return this.usuarioRepository.findUsuarioByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<Usuario> findUsuarioBySexo(String sexo){
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

    @Transactional(rollbackFor = Exception.class)
    public Usuario agregarUsuario(Usuario usuario){
        Set<Rol> roles = this.rolRepository.findRolsByNombreRol("ROLE_LECTOR");
        usuario.setRoles(roles);
        usuario.setContrasenha(new BCryptPasswordEncoder().encode(usuario.getContrasenha()));
        return this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void actualizarUsuario(Usuario usuario){
        usuario.setContrasenha(new BCryptPasswordEncoder().encode(usuario.getContrasenha()));

        this.usuarioRepository.updateUsuario(usuario.getNombre(), usuario.getApellido(), usuario.getContrasenha(),
                usuario.getCorreo(), usuario.getSexo(), usuario.getUsername(), usuario.getIdUsuario());
    }

    @Transactional(rollbackFor = Exception.class)
    public Usuario actualizarUsuarioAdmin(Usuario usuario){
        usuario.setContrasenha(new BCryptPasswordEncoder().encode(usuario.getContrasenha()));
        return this.usuarioRepository.save(usuario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void eliminarUsuario(Long idUsuario){
        this.usuarioRepository.deleteById(idUsuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = this.usuarioRepository.findUsuarioByUsername(username);
        if(usuario.isPresent()){
            return new User(usuario.get().getUsername(),
                    usuario.get().getContrasenha(),
                    usuario.get().getActivado(),
                    true,
                    true,
                    true,
                    usuario.get().getRoles().stream().map(rol -> new SimpleGrantedAuthority(
                            rol.getNombreRol())).collect(Collectors.toList()));

        }
        throw new UsernameNotFoundException("Username is not Found");
    }
}
