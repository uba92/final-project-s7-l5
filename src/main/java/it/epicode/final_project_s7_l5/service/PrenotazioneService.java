package it.epicode.final_project_s7_l5.service;

import it.epicode.final_project_s7_l5.entities.Prenotazione;
import it.epicode.final_project_s7_l5.entities.AppUser;
import it.epicode.final_project_s7_l5.entities.Evento;
import it.epicode.final_project_s7_l5.repository.PrenotazioneRepository;
import it.epicode.final_project_s7_l5.repository.AppUserRepository;
import it.epicode.final_project_s7_l5.repository.EventoRepository;
import it.epicode.final_project_s7_l5.request.PrenotazioneRequest;
import it.epicode.final_project_s7_l5.response.PrenotazioneResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EventoRepository eventoRepository;

    // Creazione di una nuova prenotazione
    public ResponseEntity<String> createPrenotazione(PrenotazioneRequest prenotazioneRequest) {
        AppUser utente = appUserRepository.findById(prenotazioneRequest.getUtenteId())
                .orElseThrow(() -> new EntityExistsException("Utente con ID: " + prenotazioneRequest.getUtenteId() + " non esistente"));

        Evento evento = eventoRepository.findById(prenotazioneRequest.getEventoId())
                .orElseThrow(() -> new EntityExistsException("Evento con ID: " + prenotazioneRequest.getEventoId() + " non esistente"));

        if (prenotazioneRepository.existsByUtenteIdAndEventoId(utente.getId(), evento.getId())) {
            throw new EntityExistsException("L'utente ha già una prenotazione per questo evento");
        }

        if (evento.getPrenotazioni().size() >= evento.getNumeroPosti()) {
            throw new EntityExistsException("Il numero massimo di posti per questo evento è stato raggiunto");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setUtente(utente);
        prenotazione.setEvento(evento);
        evento.getPrenotazioni().add(prenotazione);
        prenotazioneRepository.save(prenotazione);

        return ResponseEntity.ok("Prenotazione creata con successo");
    }

    public List<PrenotazioneResponse> getAllPrenotazioni() {
        return prenotazioneResponseFromEntityList(prenotazioneRepository.findAll());
    }

    public PrenotazioneResponse findById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityExistsException("Prenotazione con id: " + id + " non esistente"));
        return prenotazioneResponseFromEntity(prenotazione);
    }

    public void deleteById(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new EntityExistsException("Prenotazione con id: " + id + " non esistente");
        }
        prenotazioneRepository.deleteById(id);
    }

    // Metodi aggiuntivi
    public PrenotazioneResponse prenotazioneResponseFromEntity(Prenotazione prenotazione) {
        PrenotazioneResponse response = new PrenotazioneResponse();
        response.setId(prenotazione.getId());
        response.setNomeUtente(prenotazione.getUtente().getUsername());
        response.setTitoloEvento(prenotazione.getEvento().getTitolo());
        response.setDataEvento(prenotazione.getEvento().getDataEvento().toString());
        response.setLuogo(prenotazione.getEvento().getLuogo());
        return response;
    }

    public List<PrenotazioneResponse> prenotazioneResponseFromEntityList(List<Prenotazione> prenotazioni) {
        return prenotazioni.stream().map(this::prenotazioneResponseFromEntity).toList();
    }

    public PrenotazioneResponse getPrenotazioneById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityExistsException("Prenotazione con id: " + id + " non esistente"));
        return prenotazioneResponseFromEntity(prenotazione);
    }

}