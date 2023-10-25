package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgrammaFactory {

    @Autowired
    public ProgrammaFactory() {
    }
    public ProgrammaFedelta crea(Azienda azienda, ProgrammaFedeltaDTO dto) {
        if (dto.getTipo() == TipoProgramma.livelli) {
            return new ProgrammaLivelli(azienda, dto.getNome());
        } else return null;
    }
}