package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("LIVELLI")
public class ProgrammaLivelli extends ProgrammaFedelta {

    @Column(
            name = "ratio_Exp/Spesa",
            nullable = false
    )
    private double ratioExpSpesa;

    @OneToMany(mappedBy = "programma", cascade = CascadeType.ALL)
    private List<Livello> livelli;


    public ProgrammaLivelli() {
        this.livelli = new ArrayList<>();
    }

    public ProgrammaLivelli(Azienda azienda, String nome, double ratioExpSpesa) {
        super(azienda, nome);
        this.ratioExpSpesa = ratioExpSpesa;
        this.livelli = new ArrayList<>();
    }

    public void setRatioExpSpesa(double ratioExpSpesa) {
        this.ratioExpSpesa = ratioExpSpesa;
    }
}
