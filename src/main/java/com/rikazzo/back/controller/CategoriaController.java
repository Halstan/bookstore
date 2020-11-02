package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Categoria;
import com.rikazzo.back.service.CategoriaService;
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
@RequestMapping("api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    final String ENCODED = "application/json;charset=UTF-8";

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        Map<String, Object> response = new HashMap<>();
        List<Categoria> categorias = this.categoriaService.findAll();

        if(categorias.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        response.put("Cantidad de categorias", categorias.size());
        response.put("Categorias", categorias);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping( method = {RequestMethod.POST, RequestMethod.PUT}, produces = ENCODED, consumes = ENCODED)
    private ResponseEntity<?> controlarCategoria(@RequestBody @Valid Categoria categoria, BindingResult result){
        List<String> errors;
        Map<String, Object> response = new HashMap<>();
        Categoria categoria1;

        if(result.hasErrors()){
            errors = result.getFieldErrors().stream()
                    .map(err -> "El campo: " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Errores", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            categoria1 = this.categoriaService.agregarCategoria(categoria);
        }catch(DataAccessException e){
            response.put("Message", "Error al guardar a la categoría " + categoria.getNombreCategoria()  + " en la base de datos");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(categoria1, HttpStatus.OK);
    }

    @GetMapping(value = "{idCategoria}", produces = ENCODED)
    private ResponseEntity<Optional<Categoria>> findById(@PathVariable Integer idCategoria){
        Optional<Categoria> categoria = this.categoriaService.findById(idCategoria);

        if(categoria.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoria, HttpStatus.OK);
    }

    @DeleteMapping("{idCategoria}")
    private ResponseEntity<?> eliminarCategoria(@PathVariable Integer idCategoria){
        Map<String, Object> response = new HashMap<>();

        try{
            this.categoriaService.eliminarCategoria(idCategoria);
            response.put("Mensaje", "Categoría eliminado");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (DataAccessException e){
            response.put("Mensaje", "Error al eliminar la categoría");
            response.put("Error", e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
