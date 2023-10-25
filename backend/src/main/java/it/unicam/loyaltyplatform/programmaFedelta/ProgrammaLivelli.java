package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.livello.Livello;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("livelli")
public class ProgrammaLivelli extends ProgrammaFedelta {

    @Column(
            name = "ratio_Exp/Euro",
            nullable = false
    )
    private Integer ratioExpEuro;


    @OneToMany(mappedBy = "programma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livello> livelli;


    public ProgrammaLivelli() {
        this.livelli = new ArrayList<>();
    }

    public ProgrammaLivelli(Azienda azienda, String nome) {
        super(azienda, nome);
        this.ratioExpEuro = 100; //valore di default
        this.livelli = new ArrayList<>();
    }


    public void setRatioExpEuro(Integer ratioExpEuro) {
        this.ratioExpEuro = ratioExpEuro;
    }
}
