package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.premio.Premio;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.tessera.Tessera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "iscrizionelivelli")
public class IscrizioneLivelli extends Iscrizione{

    @Column
    private int livelloCorrente;

    @Column
    private double progressoLivello;

   @OneToMany(mappedBy = "iscrizionelivelli")
    private List<Premio> premiRiscattati;

   public IscrizioneLivelli(ProgrammaFedelta programmaFedelta, Tessera tessera){
       super(programmaFedelta,tessera);
       this.livelloCorrente=0;
       this.progressoLivello=0.0;
       this.premiRiscattati=new ArrayList<>();
   }
}
