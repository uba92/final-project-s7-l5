package it.epicode.final_project_s7_l5.service;

import it.epicode.final_project_s7_l5.entities.AppUser;
import it.epicode.final_project_s7_l5.entities.Evento;
import it.epicode.final_project_s7_l5.repository.EventoRepository;
import it.epicode.final_project_s7_l5.request.EventoRequest;
import it.epicode.final_project_s7_l5.response.EventoResponse;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private AppUserService appUserService;

    //creazione di un nuovo evento
    public ResponseEntity<String> createEvento(EventoRequest eventoRequest) {

        AppUser organizzatore = appUserService.findByUsername(eventoRequest.getNomeOrganizzatore()).get();

        if(eventoRepository.existsByTitolo(eventoRequest.getTitolo())) {
            throw new EntityExistsException("Evento con titolo: " + eventoRequest.getTitolo() + " già esistente");
        } else if (eventoRepository.existsByLuogoAndDataEvento(eventoRequest.getLuogo(), eventoRequest.getDataEvento())) {
            throw new EntityExistsException("Evento con luogo: " + eventoRequest.getLuogo() + " e data: " + eventoRequest.getDataEvento() + " già esistente");
        } else {
            Evento response = new Evento();
            response.setTitolo(eventoRequest.getTitolo());
            response.setDescrizione(eventoRequest.getDescrizione());
            response.setDataEvento(eventoRequest.getDataEvento());
            response.setLuogo(eventoRequest.getLuogo());
            response.setNumeroPosti(eventoRequest.getNumeroPosti());
            response.setOrganizzatore(organizzatore);
            eventoRepository.save(response);
            return ResponseEntity.ok("Evento creato con successo");
        }
    }

    public List<EventoResponse> getAllEventi() {
        return eventoresponseFromEntityList(eventoRepository.findAll());
    }

    public EventoResponse findById(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityExistsException("Evento con id: " + id + " non esistente");
        }
        Evento evento = eventoRepository.findById(id).get();
        EventoResponse response = new EventoResponse();
        BeanUtils.copyProperties(evento, response);
        return response;
    }

    public void deleteById(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityExistsException("Evento con id: " + id + " non esistente");
        }
        eventoRepository.deleteById(id);
    }

    public ResponseEntity<String> updateEvento(Long id, EventoRequest eventoRequest) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityExistsException("Evento con id: " + id + " non esistente");
        }
        Evento evento = eventoRepository.findById(id).get();
        evento.setTitolo(eventoRequest.getTitolo());
        evento.setDescrizione(eventoRequest.getDescrizione());
        evento.setDataEvento(eventoRequest.getDataEvento());
        evento.setLuogo(eventoRequest.getLuogo());
        evento.setNumeroPosti(eventoRequest.getNumeroPosti());
        eventoRepository.save(evento);
        return ResponseEntity.ok("Evento aggiornato con successo");
    }



    //metodi aggiuntivi
    public EventoResponse eventoResponseFromEntity(Evento evento) {
        EventoResponse response = new EventoResponse();
        BeanUtils.copyProperties(evento, response);
        return response;
    }

    public List<EventoResponse> eventoresponseFromEntityList(List<Evento> eventi) {
        return eventi.stream().map(this::eventoResponseFromEntity).toList();
    }
}
