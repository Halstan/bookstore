package com.rikazzo.back.controller;

import com.rikazzo.back.entity.Rol;
import com.rikazzo.back.service.RolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RolController {

    private final RolService rolService;
    final String ENCODED = "application/json;charset=UTF-8";

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping(produces = ENCODED)
    private List<Rol> findAll(){
        return this.rolService.findAll();
    }
}
