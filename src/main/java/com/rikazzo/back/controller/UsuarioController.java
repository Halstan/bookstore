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
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/usuarios")
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

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<?> agregarUsuario(@RequestBody @Valid Usuario usuario, BindingResult result){

        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Usuario usuario1 = new Usuario();

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            usuario1 = this.usuarioService.agregarUsuario(usuario);
        }catch (DataAccessException e){
            response.put("Message", "Error al guardar/actualizar al usuario " + usuario1.getNombre() + " " + usuario1.getApellido() + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
        }
        return new ResponseEntity<>(usuario1, HttpStatus.CREATED);
    }

}
