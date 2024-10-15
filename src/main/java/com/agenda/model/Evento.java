package com.agenda.model;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;

public class Evento {
    private UUID id;
    private Date dataInicio;
    private Date dataFim;
    private Time horaInicio;
    private Time horaFim;
    private String nomeEvento;
    private String descricao;
    private boolean eventoAtivo;

    public Evento() {
        this.eventoAtivo = true;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public LocalTime getHoraInicio() {
        return horaInicio.toLocalTime();
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(Time horaFim) {
        this.horaFim = horaFim;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isEventoAtivo() {
        return eventoAtivo;
    }

    public void setEventoAtivo(boolean eventoAtivo) {
        this.eventoAtivo = eventoAtivo;
    }
}
