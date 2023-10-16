package it.unicam.loyaltyplatform.livello;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.premio.Premio;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaLivelli;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "Livello")
@Table(name = "livello")
public class Livello {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name = "id_livello",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "id_programma",
            referencedColumnName = "id_programma",
            updatable = false
    )
    private ProgrammaLivelli programma;
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

    private boolean ultimoLivello;

    public Livello() {
        this.catalogoPremi = new ArrayList<>();
        this.ultimoLivello = true;
    }

    public Livello(ProgrammaLivelli programma, String nome, int expLevelUp) {
        this.programma = programma;
        this.nome = nome;
        this.expLevelUp = expLevelUp;
        this.catalogoPremi = new ArrayList<>();
        this.ultimoLivello = true;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setExpLevelUp(int expLevelUp) {
        this.expLevelUp = expLevelUp;
    }

    public void notUltimo(){
        this.ultimoLivello = false;
    }

}
