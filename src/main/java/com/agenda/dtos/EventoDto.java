package com.agenda.dtos;

import java.sql.Time;
import java.util.Date;
import java.util.UUID;

public class EventoDto {

    private UUID id;
    private Date dataInicio;
    private Date dataFim;
    private Time horaInicio;
    private Time horaFim;
    private EventoInfo evento;



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

    public Time getHoraInicio() {
        return horaInicio;
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

    public EventoInfo getEvento() {
        return evento;
    }

    public void setEvento(EventoInfo evento) {
        this.evento = evento;
    }
}
