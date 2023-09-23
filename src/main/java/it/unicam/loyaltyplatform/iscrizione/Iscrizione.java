package it.unicam.loyaltyplatform.iscrizione;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.cliente.Cliente;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.tessera.Tessera;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Cascade;

@Getter
@Entity(name = "Iscrizione")
@Table(name = "iscrizione")
public class Iscrizione {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name = "id_iscrizione",
            updatable = false
    )
    private Long iscrizioneId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "id_tessera",
            nullable = false,
            updatable = false
    )
    private Tessera tessera;


    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "id_programma",
            nullable = false,
            updatable = false
    )
    private ProgrammaFedelta programma;

    /**
     * Costruttore di default
     */
    public Iscrizione() {
    }

    public Iscrizione(ProgrammaFedelta programma, Tessera tessera) {
        this.tessera = tessera;
        this.programma = programma;
    }
}