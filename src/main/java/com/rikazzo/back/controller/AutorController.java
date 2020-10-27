package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Autor;
import com.rikazzo.back.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/autores")
public class AutorController {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping(produces = "application/json")
    private ResponseEntity<List<Autor>> findAll(){
        List<Autor> autors = this.autorService.findAll();

        return new ResponseEntity<>(autors, HttpStatus.OK);

    }
}
