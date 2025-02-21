package it.epicode.final_project_s7_l5.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "eventi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false, length = 500)
    private String descrizione;

    @Column(nullable = false)
    private Date dataEvento;

    @Column(nullable = false)
    private String luogo;

    @Column(nullable = false)
    private int numeroPosti;

    @OneToMany
    private List<Prenotazione> prenotazioni;

    @ManyToOne
    private AppUser organizzatore;

}
