package it.unicam.loyaltyplatform.premio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.livello.Livello;
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
            name = "id_livello",
            referencedColumnName = "id_livello",
            updatable = false
    )
    private Livello livello;

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

    public Premio(Livello livello, String nome, String descrizione) {
        this.livello = livello;
        this.nome = nome;
        this.descrizione = descrizione;
    }
}
