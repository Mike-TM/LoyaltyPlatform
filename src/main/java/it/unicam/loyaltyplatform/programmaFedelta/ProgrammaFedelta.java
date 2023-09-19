package it.unicam.loyaltyplatform.programmaFedelta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import it.unicam.loyaltyplatform.premio.Premio;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "ProgrammaFedelta")
@Table(name = "programma_fedelta")
public class ProgrammaFedelta {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name ="id_programma"
    )
    private Long idProgramma;

    @ManyToOne @JsonIgnore
    @JoinColumn(
            name = "id_azienda",
            referencedColumnName = "id_azienda",
            updatable = false
    )
    private Azienda azienda;

    @OneToMany(mappedBy = "programma", cascade = CascadeType.ALL)
    private List<Iscrizione> iscrizioni;

    @OneToMany(mappedBy = "programmaFedelta")
    private List<Premio> catalogoPremi;

    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "VARCHAR(40)"
    )
    private String nome;

    @Column(
            name = "num_iscrizioni",
            nullable = false,
            columnDefinition = "int default 0"
    )
    private int clientiRegistrati;


    /**
     * Costruttore di default
     */
    public ProgrammaFedelta() {
        int clientiRegistrati = 0;
        iscrizioni = new ArrayList<>();
        catalogoPremi = new ArrayList<>();
    }

    /**
     * Costruttore senza id, il quale verrà generato dal DB
     * @param azienda
     * @param nome
     */
    public ProgrammaFedelta(Azienda azienda, String nome) {
        int clientiRegistrati = 0;
        iscrizioni = new ArrayList<>();
        catalogoPremi = new ArrayList<>();
        this.azienda = azienda;
        this.nome = nome;
    }

}
