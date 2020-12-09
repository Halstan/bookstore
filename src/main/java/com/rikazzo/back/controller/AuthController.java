package com.rikazzo.back.controller;

import com.rikazzo.back.dto.AuthenticationRequest;
import com.rikazzo.back.dto.AuthenticationResponse;
import com.rikazzo.back.security.jwt.JwtUtil;
import com.rikazzo.back.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService userService;
    private final JwtUtil jwtUtil;
    final String ENCODED = "application/json;charset=UTF-8";

    public AuthController(AuthenticationManager authenticationManager, UsuarioService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(produces = ENCODED, consumes = ENCODED)
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request) {
        Map<String, Object> response = new HashMap<>();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = this.userService.loadUserByUsername(request.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            response.put("Error", e.getLocalizedMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }

}
