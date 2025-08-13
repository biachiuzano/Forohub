package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
    @NotBlank String titulo,
    @NotBlank String mensaje
) {}
