package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.cliente.Cliente;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;

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
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "id_cliente",
            nullable = false,
            updatable = false
    )
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(
            name = "id_programma",
            nullable = false,
            updatable = false
    )
    private ProgrammaFedelta programmaFedelta;

    /**
     * Costruttore di default
     */
    public Iscrizione() {
    }

    public Iscrizione(Cliente cliente, ProgrammaFedelta programmaFedelta) {
        this.cliente = cliente;
        this.programmaFedelta = programmaFedelta;
    }
}