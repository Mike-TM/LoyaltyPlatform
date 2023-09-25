package it.unicam.loyaltyplatform.programmaFedelta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.premio.Premio;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Livello {
    @Id @GeneratedValue
    @Column(
            name = "id_livello",
            updatable = false
    )
    private Long id;

    @ManyToOne @JsonIgnore
    @JoinColumn(
            name = "id_programma",
            referencedColumnName = "id_programma",
            updatable = false
    )
    private ProgrammaFedelta programma;
    private String nome;

}
