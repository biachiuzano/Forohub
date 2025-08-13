package com.example.demo.dto;

import com.example.demo.model.Topico;

import java.time.LocalDate;

public record DatosDetalleTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDate fechaCreacion,
    String status,
    String autor,
    String curso
) {
    public DatosDetalleTopico(Topico topico) {
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getFechaCreacion(),
            topico.getStatus(),
            topico.getAutor().getNombre(),
            topico.getCurso().getNombre()
        );
    }
}
