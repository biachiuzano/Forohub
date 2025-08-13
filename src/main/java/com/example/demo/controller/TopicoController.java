package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Topico;
import com.example.demo.repository.TopicoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // Crear tópico
    @PostMapping
    @Transactional
    public ResponseEntity<Void> crear(@RequestBody @Valid DatosRegistroTopico datos) {
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if (existeDuplicado) {
            return ResponseEntity.badRequest().build();
        }

        var autor = usuarioRepository.findById(datos.autorId()).orElseThrow();
        var curso = cursoRepository.findById(datos.cursoId()).orElseThrow();

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setFechaCreacion(LocalDate.now());
        topico.setStatus("ABIERTO");

        topicoRepository.save(topico);

        return ResponseEntity.ok().build();
    }

    // Listar tópicos con filtros y paginación
    @GetMapping
    public ResponseEntity<Page<DatosListarTopico>> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anho,
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion
    ) {
        Page<Topico> pagina;

        if (curso != null && anho != null) {
            var inicio = LocalDate.of(anho, 1, 1);
            var fin = LocalDate.of(anho, 12, 31);
            pagina = topicoRepository.findByCursoNombreContainingIgnoreCaseAndFechaCreacionBetween(
                    curso, inicio, fin, paginacion
            );
        } else {
            pagina = topicoRepository.findAll(paginacion);
        }

        var dto = pagina.map(DatosListarTopico::new);
        return ResponseEntity.ok(dto);
    }

    // Listar top 10 por fecha
    @GetMapping("/top10")
    public ResponseEntity<List<DatosListarTopico>> listarTop10PorFecha() {
        var lista = topicoRepository.findTop10ByOrderByFechaCreacionAsc()
                .stream()
                .map(DatosListarTopico::new)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // Obtener detalle por ID
    @GetMapping("/{id}")
    public ResponseEntity<DatosListarTopico> detalle(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            var dto = new DatosListarTopico(topicoOptional.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar tópico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> actualizar(@PathVariable Long id, @RequestBody @Valid DatosRegistroTopico datos) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoOptional.get();

        // Actualizamos solo los campos que vienen
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());

        var autor = usuarioRepository.findById(datos.autorId()).orElseThrow();
        var curso = cursoRepository.findById(datos.cursoId()).orElseThrow();

        topico.setAutor(autor);
        topico.setCurso(curso);

        return ResponseEntity.noContent().build();
    }

    // Eliminar tópico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
