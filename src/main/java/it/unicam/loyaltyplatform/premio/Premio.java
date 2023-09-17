package it.unicam.loyaltyplatform.premio;

import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;

@Entity @Table
public class Premio {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_programma", nullable = false, updatable = false)
    private ProgrammaFedelta programmaFedelta;



}
