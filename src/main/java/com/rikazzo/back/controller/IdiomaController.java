package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Idioma;
import com.rikazzo.back.service.IdiomaService;
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
@RequestMapping("api/idiomas")
public class IdiomaController {

    private final IdiomaService idiomaService;
    final String ENCODED = "application/json;charset=UTF-8";
    List<String> errors;
    Map<String, Object> response = new HashMap<>();

    @Autowired
    public IdiomaController(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        List<Idioma> idiomas = this.idiomaService.findAll();

        if (idiomas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(idiomas, HttpStatus.OK);
    }

    @GetMapping(value = "{idIdioma}", produces = ENCODED)
    private ResponseEntity<Optional<Idioma>> findById(@PathVariable Integer idIdioma){
        Optional<Idioma> idioma = this.idiomaService.findById(idIdioma);

        if (idioma.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(idioma, HttpStatus.OK);

    }

    @RequestMapping( method = {RequestMethod.POST, RequestMethod.PUT}, produces = ENCODED, consumes = ENCODED)
    private ResponseEntity<?> controlarIdioma(@RequestBody @Valid Idioma idioma, BindingResult result){
        Idioma idioma1;

        if(result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            idioma1 = this.idiomaService.agregarIdioma(idioma);
        }catch(DataAccessException e){
            response.put("Message", "Error al guardar el idioma " + idioma.getNombreIdioma()  + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(idioma1, HttpStatus.OK);
    }

    @DeleteMapping("{idIdioma}")
    private ResponseEntity<?> eliminarIdioma(@PathVariable Integer idIdioma){

        try {
            this.idiomaService.eliminarIdioma(idIdioma);
            response.put("Mensaje", "Idioma eliminado exitosamente");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar el idioma");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
