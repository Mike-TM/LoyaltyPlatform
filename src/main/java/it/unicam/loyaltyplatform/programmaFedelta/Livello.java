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
    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "VARCHAR(40)"
    )
    private String nome;

    @Column(
            name = "expLevelUp",
            nullable = false
    )
    private int expLevelUp;

    @OneToMany(cascade = CascadeType.ALL)
    @MapKey(name = "livello")
    private List<Premio> catalogoPremi;
}
