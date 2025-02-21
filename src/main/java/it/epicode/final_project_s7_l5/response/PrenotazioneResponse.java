package it.epicode.final_project_s7_l5.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrenotazioneResponse {

    private Long id;
    private String nomeUtente;
    private String titoloEvento;
    private String dataEvento;
    private String luogo;

    public PrenotazioneResponse(Long id, String nomeUtente, String titoloEvento, String dataEvento, String luogo) {
        this.id = id;
        this.nomeUtente = nomeUtente;
        this.titoloEvento = titoloEvento;
        this.dataEvento = dataEvento;
        this.luogo = luogo;
    }
}
