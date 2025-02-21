package it.epicode.final_project_s7_l5.controller;

import it.epicode.final_project_s7_l5.request.PrenotazioneRequest;
import it.epicode.final_project_s7_l5.response.PrenotazioneResponse;
import it.epicode.final_project_s7_l5.service.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    public ResponseEntity<String> createPrenotazione(@RequestBody PrenotazioneRequest prenotazioneRequest) {
        return prenotazioneService.createPrenotazione(prenotazioneRequest);
    }

    @GetMapping
    public List<PrenotazioneResponse> getAllPrenotazioni() {
        return prenotazioneService.getAllPrenotazioni();
    }

    @GetMapping("/{id}")
    public PrenotazioneResponse getPrenotazioneById(@PathVariable Long id) {
        return prenotazioneService.getPrenotazioneById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        prenotazioneService.deleteById(id);
    }
}
