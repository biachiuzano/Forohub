package com.example.demo.dto;

import com.example.demo.model.Topico;
import java.time.LocalDate;

public record DatosListarTopico(
    Long id,
    String titulo,
    String mensaje,
    String autor,
    String curso,
    LocalDate fechaCreacion,
    String status
) {
    public DatosListarTopico(Topico topico) {
        this(
            topico.getId(),
            topico.getTitulo(),
            topico.getMensaje(),
            topico.getAutor().getNombre(),
            topico.getCurso().getNombre(),
            topico.getFechaCreacion(),
            topico.getStatus()
        );
    }
}
