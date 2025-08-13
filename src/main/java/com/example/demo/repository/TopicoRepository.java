package com.example.demo.repository;

import com.example.demo.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    // Verifica duplicados por título y mensaje
    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    // Ordenados por fecha de creación ascendente
    List<Topico> findTop10ByOrderByFechaCreacionAsc();

    // Filtro por nombre de curso (parcial y sin importar mayúsculas) y rango de fechas
    Page<Topico> findByCursoNombreContainingIgnoreCaseAndFechaCreacionBetween(
        String cursoNombre,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        Pageable pageable
    );

}
