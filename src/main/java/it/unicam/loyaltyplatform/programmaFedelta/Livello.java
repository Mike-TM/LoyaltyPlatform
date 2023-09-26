package it.unicam.loyaltyplatform.programmaFedelta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.premio.Premio;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "Livello")
@Table(name = "livello")
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

    @OneToMany(mappedBy = "livello", cascade = CascadeType.ALL)
    private List<Premio> catalogoPremi;

    public Livello() {
        this.catalogoPremi = new ArrayList<>();
    }
    public Livello(ProgrammaFedelta programma, String nome, int expLevelUp) {
        this.programma = programma;
        this.nome = nome;
        this.expLevelUp = expLevelUp;
        this.catalogoPremi = new ArrayList<>();
    }
}
