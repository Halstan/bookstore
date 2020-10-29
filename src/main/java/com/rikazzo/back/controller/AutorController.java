package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Autor;
import com.rikazzo.back.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        List<Autor> autors = this.autorService.findAll();

        return new ResponseEntity<>(autors, HttpStatus.OK);
    }

    @PostMapping(consumes = ENCODED, produces = ENCODED)
    private ResponseEntity<Autor> agregarAutor(@RequestBody @Valid Autor autor, BindingResult result){
        Autor autor1 = this.autorService.agregarAutor(autor);

        return new ResponseEntity<>(autor1, HttpStatus.OK);
    }
}
