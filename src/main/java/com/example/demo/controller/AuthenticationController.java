package com.example.demo.controller;

import com.example.demo.dto.DatosAutenticacionUsuario;
import com.example.demo.security.jwt.TokenService;
import com.example.demo.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<String> autenticar(@RequestBody @Valid DatosAutenticacionUsuario datos) {
        var authToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasena());
        var auth = authenticationManager.authenticate(authToken);
        var usuario = (Usuario) auth.getPrincipal();
        var jwtToken = tokenService.generarToken(usuario);
        return ResponseEntity.ok(jwtToken);
    }
}
