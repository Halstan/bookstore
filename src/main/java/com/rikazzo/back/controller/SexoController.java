package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Sexo;
import com.rikazzo.back.service.SexoService;
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
@RequestMapping("api/sexos")
public class SexoController {

    private final SexoService sexoService;
    final String ENCODED = "application/json;charset=UTF-8";

    @Autowired
    public SexoController(SexoService sexoService) {
        this.sexoService = sexoService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        Map<String, Object> response = new HashMap<>();
        List<Sexo> sexos = this.sexoService.findAll();

        if (sexos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        response.put("Cantidad de sexos", sexos.size());
        response.put("Sexos", sexos);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "{idSexo}", produces = ENCODED)
    private ResponseEntity<Optional<Sexo>> findById(@PathVariable Short idSexo){
        Optional<Sexo> sexo = this.sexoService.findById(idSexo);

        if (sexo.isPresent()){
            return new ResponseEntity<>(sexo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping( method = {RequestMethod.POST, RequestMethod.PUT}, produces = ENCODED, consumes = ENCODED)
    private ResponseEntity<?> controlarSexo(@RequestBody @Valid Sexo sexo, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Sexo sexo1 = new Sexo();

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }

        try{
            sexo1 = this.sexoService.agregarSexo(sexo);
        }catch (DataAccessException e){
            response.put("Message", "Error al guardar/actualizar el sexo " + sexo1.getNombreSexo() + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
        }
        return new ResponseEntity<>(sexo1, HttpStatus.CREATED);
    }

    @DeleteMapping("{idSexo}")
    private ResponseEntity<?> eliminarSexo(@PathVariable Short idSexo){
        Map<String, Object> response = new HashMap<>();

        try {
            this.sexoService.eliminarSexo(idSexo);
            response.put("Mensaje", "Sexo eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar el alquiler");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
