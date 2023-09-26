package it.unicam.loyaltyplatform.programmaFedelta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import it.unicam.loyaltyplatform.premio.Premio;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.mapping.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_programma")
@Entity(name = "ProgrammaFedelta")
@Table(name = "programma_fedelta")
public abstract class ProgrammaFedelta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id_programma"
    )
    private Long programmaId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "id_azienda",
            referencedColumnName = "id_azienda",
            updatable = false
    )
    private Azienda azienda;

    @OneToMany(mappedBy = "programma", cascade = CascadeType.ALL)
    private List<Iscrizione> iscrizioni;

    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "VARCHAR(40)"
    )
    private String nome;


    /**
     * Costruttore di default
     */
    public ProgrammaFedelta() {
        iscrizioni = new ArrayList<>();
    }

    /**
     * Costruttore senza id, il quale verrà generato dal DB
     *
     * @param azienda
     * @param nome
     */
    public ProgrammaFedelta(Azienda azienda, String nome) {

        iscrizioni = new ArrayList<>();
        this.azienda = azienda;
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
