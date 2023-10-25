package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.premio.Premio;
import it.unicam.loyaltyplatform.livello.Livello;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaLivelli;
import it.unicam.loyaltyplatform.tessera.Tessera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("livelli")
public class IscrizioneLivelli extends Iscrizione{

    @OneToOne
    private Livello livelloCorrente;

    @Column(
            name = "progressoLivello",
            nullable = false
    )
    private double progressoLivello;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Premio> premiRiscattati;

    public IscrizioneLivelli(ProgrammaLivelli programmaFedelta, Tessera tessera){
       super(programmaFedelta,tessera);

       this.progressoLivello=0.0;
       this.premiRiscattati=new ArrayList<>();
       this.livelloCorrente=programmaFedelta.getLivelli().get(0);
    }
}
