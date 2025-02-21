package it.epicode.final_project_s7_l5.repository;

import it.epicode.final_project_s7_l5.entities.Prenotazione;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository {

    boolean exsistsByUtenteId(Long idUtente);

    Prenotazione findById(Long id);

    List<Prenotazione> findAllByUtenteId(Long idUtente);

}
