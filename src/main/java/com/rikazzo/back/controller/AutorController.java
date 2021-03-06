package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Autor;
import com.rikazzo.back.mapper.AutorMapper;
import com.rikazzo.back.mapper.AutorMapperImpl;
import com.rikazzo.back.service.AutorService;
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
@RequestMapping("autores")
public class AutorController {

    private final AutorMapper autorMapper;
    private final AutorService autorService;
    final String ENCODED = "application/json;charset=UTF-8";

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
        autorMapper = new AutorMapperImpl();
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        List<Autor> autores = this.autorService.findAll();

        if (autores.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(autorMapper.toAutorDTOs(autores), HttpStatus.OK);
    }

    @RequestMapping( method = {RequestMethod.POST, RequestMethod.PUT}, produces = ENCODED, consumes = ENCODED)
    private ResponseEntity<?> controlarAutor(@RequestBody @Valid Autor autor, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Autor autor1;

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            autor1 = this.autorService.agregarAutor(autor);
        }catch (DataAccessException e){
            response.put("Message", "Error al guardar al autor " + autor.getNombreAutor() + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(autor1, HttpStatus.OK);
    }

    @GetMapping(value = "/nombre/{nombre}", produces = ENCODED)
    private ResponseEntity<List<Autor>> findAutorByNombre(@PathVariable String nombre){
        List<Autor> autores = this.autorService.findByNombre(nombre);

        if (autores.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @GetMapping("/{idAutor}")
    private ResponseEntity<?> findAutor(@PathVariable Integer idAutor){
        Optional<Autor> autor = this.autorService.findById(idAutor);
        Map<String, Object> response = new HashMap<>();

        if (autor.isEmpty()){
            response.put("Error", "Este autor no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(autor, HttpStatus.OK);
    }

    @DeleteMapping("/{idAutor}")
    private ResponseEntity<?> eliminarAutor(@PathVariable Integer idAutor){
        Map<String, Object> response = new HashMap<>();

        try{
            this.autorService.eliminarAutor(idAutor);
            response.put("Mensaje", "Autor eliminado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar el autor");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
