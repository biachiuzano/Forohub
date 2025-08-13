package com.example.demo.dto;

import com.example.demo.model.Topico;

public record DatosListadoTopico(Long id, String titulo, String mensaje) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje());
    }
}
