package it.epicode.final_project_s7_l5.controller;

import it.epicode.final_project_s7_l5.request.EventoRequest;
import it.epicode.final_project_s7_l5.response.EventoResponse;
import it.epicode.final_project_s7_l5.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventi")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<String> createEvento(@RequestBody EventoRequest eventoRequest) {
        eventoService.createEvento(eventoRequest);
        return ResponseEntity.ok("Evento creato con successo");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<EventoResponse>> getAllEventi() {
        return ResponseEntity.ok(eventoService.getAllEventi());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EventoResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(eventoService.findById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        eventoService.deleteById(id);
        return ResponseEntity.ok("Evento eliminato con successo");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateEvento(@PathVariable Long id, @RequestBody EventoRequest eventoRequest) {
        return eventoService.updateEvento(id, eventoRequest);
    }
}
