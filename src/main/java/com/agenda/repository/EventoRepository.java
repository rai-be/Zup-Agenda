package com.agenda.repository;

import com.agenda.model.Evento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EventoRepository {

    private final List<Evento> eventos = new ArrayList<>();

    public Evento save(Evento evento) {
        eventos.removeIf(e -> e.getId().equals(evento.getId()));
        eventos.add(evento);
        return evento;
    }

    public List<Evento> findAll() {
        return eventos;
    }

    public Optional<Evento> findById(UUID id) {
        return eventos.stream().filter(evento -> evento.getId().equals(id)).findFirst();
    }

    public void delete(UUID id) {
        eventos.removeIf(evento -> evento.getId().equals(id));
    }

    public boolean existsById(UUID id) {
        return false;
    }

    public void deleteById(UUID id) {
    }
}
