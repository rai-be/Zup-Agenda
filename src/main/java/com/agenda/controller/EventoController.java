package com.agenda.controller;

import com.agenda.dtos.EventoDto;
import com.agenda.model.Evento;
import com.agenda.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<?> criarEvento(@RequestBody EventoDto eventoDto) {
        List<String> erros = eventoService.validarEvento(eventoDto);
        if (!erros.isEmpty()) {
            return ResponseEntity.unprocessableEntity().body(erros);
        }

        Evento evento = eventoService.criarEvento(eventoDto);
        return new ResponseEntity<>(evento, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        List<Evento> eventos = eventoService.listarEventos();
        return ResponseEntity.ok(eventos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> apagarEvento(@PathVariable UUID id) {
        eventoService.apagarEvento(id);
        return ResponseEntity.ok("Evento apagado com sucesso.");
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarEvento(@PathVariable UUID id) {
        eventoService.cancelarEvento(id);
        return ResponseEntity.ok("Evento cancelado com sucesso.");
    }
}
