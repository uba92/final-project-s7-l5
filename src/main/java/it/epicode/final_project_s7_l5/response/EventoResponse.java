package it.epicode.final_project_s7_l5.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class EventoResponse {

    private Long id;
    private String titolo;
    private String descrizione;
    private Date dataEvento;
    private String luogo;
    private int numeroPosti;
}
