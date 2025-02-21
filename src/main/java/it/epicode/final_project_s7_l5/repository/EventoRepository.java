package it.epicode.final_project_s7_l5.repository;

import it.epicode.final_project_s7_l5.entities.Evento;
import it.epicode.final_project_s7_l5.response.EventoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    boolean existsByTitolo(String titolo);

    boolean existsByLuogoAndDataEvento(String luogo, Date dataEvento);

    Optional<Evento> findByTitolo(String titolo);


}
