package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Alquiler;
import com.rikazzo.back.service.AlquilerService;
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
@RequestMapping("alquileres")
public class AlquilerController {

    private final AlquilerService alquilerService;
    private final LibroService libroService;
    final String ENCODED = "application/json;charset=UTF-8";

    @Autowired
    public AlquilerController(AlquilerService alquilerService, LibroService libroService) {
        this.alquilerService = alquilerService;
        this.libroService = libroService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<List<Alquiler>> findAll(){
        List<Alquiler> alquilers = this.alquilerService.findAll();

        if(alquilers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alquilers, HttpStatus.OK);
    }

    @GetMapping(value = "/usuario/{username}", produces = ENCODED)
    private ResponseEntity<List<Alquiler>> findByUsername(@PathVariable String username){
        List<Alquiler> alquilers = this.alquilerService.findAlquilerByUsername(username);

        if(alquilers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alquilers, HttpStatus.OK);

    }

    @GetMapping(value = "/estado/{estado}", produces = ENCODED)
    private ResponseEntity<List<Alquiler>> findByEstado(@PathVariable boolean estado){
        List<Alquiler> alquilers = this.alquilerService.findAlquilerByEstado(estado);

        if (alquilers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alquilers, HttpStatus.OK);
    }

    @GetMapping(value = "/{idAlquiler}", produces = ENCODED)
    private ResponseEntity<Optional<Alquiler>> findById(@PathVariable Long idAlquiler){
        Optional<Alquiler> alquiler = this.alquilerService.findById(idAlquiler);

        if (alquiler.isPresent()){
            return new ResponseEntity<>(alquiler, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(produces = ENCODED, consumes = ENCODED)
    private ResponseEntity<?> agregarAlquiler(@RequestBody @Valid Alquiler alquiler, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Alquiler alquiler1;

        if (result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }

        try{
            this.libroService.setEstadoFale(alquiler.getLibro().getIdLibro());
            alquiler1 = this.alquilerService.agregarAlquiler(alquiler);
        }catch (DataAccessException e){
            response.put("Message", "Error al guardar el alquiler en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(alquiler1, HttpStatus.CREATED);
    }

    @DeleteMapping("{idAlquiler}")
    private ResponseEntity<?> desactivarAlquiler(@PathVariable Long idAlquiler){
        Map<String, Object> response = new HashMap<>();
        Optional<Alquiler> alquiler = this.alquilerService.findById(idAlquiler);

        if(alquiler.isPresent()){
            try{
                this.alquilerService.setEstadoFalse(alquiler.get().getIdAlquiler());
                this.libroService.setEstadoTrue(alquiler.get().getLibro().getIdLibro());
                response.put("Mensaje", "El alquiler ha sido desactivado con éxito");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }catch (DataAccessException e){
                response.put("Mensaje", "Error al desactivar el alquiler");
                response.put("Error", e.getMostSpecificCause().getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/eliminar/{idAlquiler}")
    private ResponseEntity<?> eliminarAlquiler(@PathVariable Long idAlquiler){
        Map<String, Object> response = new HashMap<>();

        try{
            this.alquilerService.deleteAlquiler(idAlquiler);
            response.put("Mensaje", "El alquiler ha sido eliminado con éxito");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar el alquiler");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
