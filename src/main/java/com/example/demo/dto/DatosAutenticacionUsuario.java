package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
    @NotBlank String correoElectronico,
    @NotBlank String contrasena
) {}
