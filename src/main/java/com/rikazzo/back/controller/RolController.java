package com.rikazzo.back.controller;

import com.rikazzo.back.mapper.RolMapper;
import com.rikazzo.back.mapper.RolMapperImpl;
import com.rikazzo.back.service.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
public class RolController {

    private final RolService rolService;
    private final RolMapper rolMapper;
    final String ENCODED = "application/json;charset=UTF-8";

    public RolController(RolService rolService) {
        this.rolService = rolService;
        rolMapper = new RolMapperImpl();
    }

    @GetMapping(produces = ENCODED)
    private ResponseEntity<?> findAll(){
        return new ResponseEntity<>(rolMapper.toRolDtos(this.rolService.findAll()), HttpStatus.OK);
    }
}
