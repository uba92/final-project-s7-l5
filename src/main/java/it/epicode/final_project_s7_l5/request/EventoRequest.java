package it.epicode.final_project_s7_l5.request;

import lombok.Data;

import java.util.Date;

@Data
public class EventoRequest {

    private String titolo;
    private String descrizione;
    private Date dataEvento;
    private String luogo;
    private int numeroPosti;
    private String nomeOrganizzatore;
}
