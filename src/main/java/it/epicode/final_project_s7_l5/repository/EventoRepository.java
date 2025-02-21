package it.epicode.final_project_s7_l5.repository;

import it.epicode.final_project_s7_l5.entities.Evento;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventoRepository {

    boolean exsistsByTitolo(String titolo);

    boolean exsistByLuogoAndDataEvento(String luogo, Date dataEvento);

    Optional<Evento> findByTitolo(String titolo);
}
