package it.unicam.loyaltyplatform.premio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "Premio")
@Table(name = "premio")
public class Premio {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name ="id_premio"
    )
    private Long premioId;

    @ManyToOne @JsonIgnore
    @JoinColumn(
            name = "id_programma",
            referencedColumnName = "id_programma",
            updatable = false
    )
    private ProgrammaFedelta programmaFedelta;

    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "VARCHAR(40)"
    )
    private String nome;

    @Column(
            name = "descrizione",
            columnDefinition = "TEXT"
    )
    private String descrizione;

    public Premio(ProgrammaFedelta programmaFedelta, String nome, String descrizione) {
        this.programmaFedelta = programmaFedelta;
        this.nome = nome;
        this.descrizione = descrizione;
    }
}
