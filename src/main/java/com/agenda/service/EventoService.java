package com.agenda.service;

import com.agenda.dtos.EventoDto;
import com.agenda.model.Evento;
import com.agenda.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<String> validarEvento(EventoDto eventoDto) {
        List<String> erros = new ArrayList<>();
        if (eventoDto.getDataInicio() == null) {
            erros.add("dataInicio é obrigatório.");
        }
        if (eventoDto.getDataFim() == null) {
            erros.add("dataFim é obrigatório.");
        }
        if (eventoDto.getHoraInicio() == null) {
            erros.add("horaInicio é obrigatório.");
        }
        if (eventoDto.getHoraFim() == null) {
            erros.add("horaFim é obrigatório.");
        }
        if (eventoDto.getEvento() == null || eventoDto.getEvento().getNomeEvento() == null) {
            erros.add("nomeEvento é obrigatório.");
        }
        if (eventoDto.getEvento() == null || eventoDto.getEvento().getDescricao() == null) {
            erros.add("descricao é obrigatório.");
        }
        return erros;
    }

    public Evento criarEvento(EventoDto eventoDto) {
        List<String> erros = validarEvento(eventoDto);
        if (!erros.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", erros));
        }

        Evento evento = new Evento();
        evento.setId(UUID.randomUUID());
        evento.setDataInicio(eventoDto.getDataInicio());
        evento.setDataFim(eventoDto.getDataFim());
        evento.setHoraInicio(eventoDto.getHoraInicio());
        evento.setHoraFim(eventoDto.getHoraFim());
        evento.setNomeEvento(eventoDto.getEvento().getNomeEvento());
        evento.setDescricao(eventoDto.getEvento().getDescricao());
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public List<Evento> listarEventos(Integer dias) {
        List<Evento> todosEventos = eventoRepository.findAll();
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now(ZoneId.systemDefault());
        System.out.println("Hora atual: " + agora);

        if (dias != null) {
            if (dias < 0) {
                return todosEventos.stream()
                        .filter(evento -> {
                            LocalDate dataEvento = evento.getDataInicio().toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
                            LocalTime horaEvento = evento.getHoraInicio();

                            ZonedDateTime eventoZoned = ZonedDateTime.of(dataEvento, horaEvento, ZoneId.of("UTC"));

                            ZonedDateTime eventoLocal = eventoZoned.withZoneSameInstant(ZoneId.systemDefault());

                            System.out.println("Evento: " + eventoLocal);

                            return dataEvento.isBefore(hoje) ||
                                    (dataEvento.isEqual(hoje) && eventoLocal.toLocalTime().isBefore(agora));
                        })
                        .collect(Collectors.toList());
            } else {
                return todosEventos.stream()
                        .filter(evento -> {
                            LocalDate dataEvento = evento.getDataInicio().toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
                            LocalTime horaEvento = evento.getHoraInicio();


                            ZonedDateTime eventoZoned = ZonedDateTime.of(dataEvento, horaEvento, ZoneId.of("UTC"));


                            ZonedDateTime eventoLocal = eventoZoned.withZoneSameInstant(ZoneId.systemDefault());

                            System.out.println("Evento: " + eventoLocal);

                            return dataEvento.isAfter(hoje) ||
                                    (dataEvento.isEqual(hoje) && eventoLocal.toLocalTime().isAfter(agora));
                        })
                        .collect(Collectors.toList());
            }
        }

        return todosEventos;
    }

    public void apagarEvento(UUID id) {
        if (!eventoRepository.findById(id).isPresent()) {
            throw new RuntimeException("Evento não encontrado");
        }
        eventoRepository.delete(id);
    }

    public void cancelarEvento(UUID id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        evento.setEventoAtivo(false);
        eventoRepository.save(evento);
    }
}
