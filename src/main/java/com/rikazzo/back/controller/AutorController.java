package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Autor;
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
@RequestMapping("api/autores")
public class AutorController {

    private final AutorService autorService;
    final String ENCODED = "application/json;charset=UTF-8";

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<List<Autor>> findAll(){
        List<Autor> autores = this.autorService.findAll();

        if (autores.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(autores, HttpStatus.OK);

    }

    @PostMapping(consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<?> agregarAutor(@RequestBody @Valid Autor autor, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Autor autor1 = new Autor();

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getDefaultMessage() + "no puede estar vacío")
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            autor1 = this.autorService.agregarAutor(autor);
        }catch (DataAccessException e){
            response.put("Message", "Error al guardar al autor " + autor1.getNombreAutor()  + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
        }

        return new ResponseEntity<>(autor1, HttpStatus.OK);
    }

    @GetMapping(value = "/nombre/{nombre}", produces = ENCODED)
    private ResponseEntity<List<Autor>> findAutorByNombre(@PathVariable String nombre){
        List<Autor> autores = this.autorService.findByNombre(nombre);

        if (autores.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @GetMapping("/{idAutor}")
    private ResponseEntity<Optional<Autor>> findAutor(@PathVariable Integer idAutor){
        Optional<Autor> autor = this.autorService.findById(idAutor);

        if (autor.isPresent()){
            return new ResponseEntity<>(autor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
