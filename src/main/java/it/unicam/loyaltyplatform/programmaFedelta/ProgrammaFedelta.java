package it.unicam.loyaltyplatform.programmaFedelta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.premio.Premio;
import jakarta.persistence.*;
import lombok.Getter;

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

    @ManyToOne
    @JoinColumn(
            name = "id_azienda",
            referencedColumnName = "id_azienda",
            nullable = false,
            updatable = false
    )
    private Azienda azienda;

    @JsonIgnore
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

    public ProgrammaFedelta() {
        int clientiRegistrati = 0;
    }

    public ProgrammaFedelta(Long idProgramma, Azienda azienda) {
        this.idProgramma = idProgramma;
        this.clientiRegistrati =  0;
        this.azienda = azienda;
    }

    public ProgrammaFedelta(Azienda azienda) {
        this.clientiRegistrati = 0;
        this.azienda = azienda;
    }

}
