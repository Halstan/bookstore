package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Usuario;
import com.rikazzo.back.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    final String ENCODED = "application/json;charset=UTF-8";

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        Map<String, Object> response = new HashMap<>();
        List<Usuario> usuarios = this.usuarioService.findAll();

        if (usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.put("Cantidad de usuarios", usuarios.size());
        response.put("Usuarios", usuarios);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{idUsuario}", produces = ENCODED)
    private ResponseEntity<Optional<Usuario>> findById(@PathVariable Long idUsuario){
        Optional<Usuario> libro = this.usuarioService.findById(idUsuario);

        if (libro.isPresent()){
            return new ResponseEntity<>(libro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/nombre/{nombre}", produces = ENCODED)
    private ResponseEntity<?> findByNombre(@PathVariable String nombre){
        Map<String, Object> response = new HashMap<>();
        List<Usuario> usuarios = this.usuarioService.findByNombre(nombre);

        if (usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.put("Cantidad de usuarios", usuarios.size());
        response.put("Usuarios", usuarios);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/sexo/{sexo}", produces = ENCODED)
    private ResponseEntity<?> findBySexo(@PathVariable String sexo){
        Map<String, Object> response = new HashMap<>();
        List<Usuario> usuarios = this.usuarioService.findUsuarioBySexo(sexo);

        if (usuarios.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.put("Cantidad de usuarios", usuarios.size());
        response.put("Usuarios", usuarios);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/username/{username}", produces = ENCODED)
    private ResponseEntity<?> getUsername(@PathVariable String username){
        Optional<Usuario> usuario = this.usuarioService.findByUsername(username);
        if (usuario.isPresent()){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<?> agregarUsuario(@RequestBody @Valid Usuario usuario, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Usuario usuario1;

        if (result.hasErrors() || !usuario.getContrasenha().equals(usuario.getAsegurarContrasenha())){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            errors.add("Las contraseñas no coinciden");
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            usuario1 = this.usuarioService.agregarUsuario(usuario);
        }catch (DataAccessException e){
            response.put("Message", "Error al actualizar al usuario " + usuario.getNombre() + " " + usuario.getApellido() + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuario1, HttpStatus.CREATED);
    }

    @PutMapping(consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<?> actualizarUsuario(@RequestBody @Valid Usuario usuario, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors() || !usuario.getContrasenha().equals(usuario.getAsegurarContrasenha())){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            errors.add("Las contraseñas no coinciden");
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            this.usuarioService.actualizarUsuario(usuario);
        }catch (DataAccessException e){
            response.put("Message", "Error al actualizar al usuario " + usuario.getNombre() + " " + usuario.getApellido() + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PutMapping(value = "admin", consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<?> actualizarUsuarioAdmin(@RequestBody @Valid Usuario usuario, BindingResult result){

        List<String> errors;
        Usuario usuario1;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            errors.add("Las contraseñas no coinciden");
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            usuario1 = this.usuarioService.actualizarUsuarioAdmin(usuario);
        }catch (DataAccessException e){
            response.put("Message", "Error al actualizar al usuario " + usuario.getNombre() + " " + usuario.getApellido() + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(usuario1, HttpStatus.CREATED);
    }

    @DeleteMapping("{idUsuario}")
    private ResponseEntity<?> eliminarUsuario(@PathVariable Long idUsuario){
        Map<String, Object> response = new HashMap<>();

        try{
            this.usuarioService.eliminarUsuario(idUsuario);
            response.put("Mensaje", "El usuario ha sido eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar el usuario");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
