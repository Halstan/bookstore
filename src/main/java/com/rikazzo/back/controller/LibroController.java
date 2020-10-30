package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Libro;
import com.rikazzo.back.service.LibroService;
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
@RequestMapping("api/libros")
public class LibroController {

    private final LibroService libroService;
    final String ENCODED = "application/json;charset=UTF-8";

    @Autowired
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        Map<String, Object> response = new HashMap<>();
        List<Libro> libros = this.libroService.findAll();

        if (libros.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.put("Cantidad de libros", libros.size());
        response.put("Libros", libros);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "estado/{estado}", produces = ENCODED)
    private ResponseEntity<?> findByEstado(@PathVariable Boolean estado){
        Map<String, Object> response = new HashMap<>();
        List<Libro> libros = this.libroService.findByEstado(estado);

        if (libros.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.put("Cantidad de libros", libros.size());
        response.put("Libros", libros);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{idLibro}")
    private ResponseEntity<Optional<Libro>> findById(@PathVariable Long idLibro){
        Optional<Libro> libro = this.libroService.findById(idLibro);

        if (libro.isPresent()){
            return new ResponseEntity<>(libro, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = ENCODED, consumes = ENCODED)
    private ResponseEntity<?> controlarLibro(@RequestBody @Valid Libro libro, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Libro libro1 = new Libro();

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            libro1 = this.libroService.agregarLibro(libro);
        }catch (DataAccessException e){
            response.put("Message", "Error al guardar/actualizar el libro " + libro1.getNombreLibro()  + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
        }

        return new ResponseEntity<>(libro1, HttpStatus.CREATED);
    }

    @DeleteMapping("{idLibro}")
    private ResponseEntity<?> eliminarLibro(@PathVariable Long idLibro){
        Map<String, Object> response = new HashMap<>();

        try {
            this.libroService.eliminarLibro(idLibro);
            response.put("Mensaje", "El libro ha sido eliminada con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar el libro");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
