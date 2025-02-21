package it.epicode.final_project_s7_l5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prenotazioni")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private AppUser utente;

    @ManyToOne
    private Evento evento;
}
