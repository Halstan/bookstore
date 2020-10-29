package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Editorial;
import com.rikazzo.back.service.EditorialService;
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
@RequestMapping("api/editoriales")
public class EditorialController {

    private final EditorialService editorialService;
    final String ENCODED = "application/json;charset=UTF-8";

    @Autowired
    public EditorialController(EditorialService editorialService) {
        this.editorialService = editorialService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        Map<String, Object> response = new HashMap<>();
        List<Editorial> editoriales = this.editorialService.findAll();

        Integer cantidad = editoriales.size();


        if (editoriales.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.put("Cantidad de editoriales", cantidad);
        response.put("Editoriales", editoriales);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{idEditorial}", produces = ENCODED)
    private ResponseEntity<Optional<Editorial>> findById(@PathVariable Integer idEditorial){
        Optional<Editorial> editorial = this.editorialService.findById(idEditorial);

        if (editorial.isPresent()){
            return new ResponseEntity<>(editorial, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping(consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<?> agregarEditorial(@RequestBody @Valid Editorial editorial, BindingResult result){

        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Editorial editorial1 = new Editorial();

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getDefaultMessage() + "no puede estar vacío")
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            editorial1 = this.editorialService.agregarEditorial(editorial);
        }catch (DataAccessException e){
            response.put("Message", "Error al guardar a la editorial " + editorial1.getNombreEditorial()  + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
        }

        return new ResponseEntity<>(editorial1, HttpStatus.CREATED);
    }

    @PutMapping(consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<?> actualizarEditorial(@RequestBody @Valid Editorial editorial, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Editorial editorial1 = this.editorialService.actualizarEditorial(editorial);

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getDefaultMessage() + "no puede estar vacío")
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(editorial1, HttpStatus.OK);
    }

    @DeleteMapping("/{idEditorial}")
    private ResponseEntity<?> eliminarEditorial(@PathVariable Integer idEditorial){
        Map<String, Object> response = new HashMap<>();

        try {
            this.editorialService.eliminarEditorial(idEditorial);
            response.put("Mensaje", "La editorial ha sido eliminada con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar a la editorial");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
