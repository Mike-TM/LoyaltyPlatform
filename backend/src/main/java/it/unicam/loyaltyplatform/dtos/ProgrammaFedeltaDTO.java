package it.unicam.loyaltyplatform.dtos;

import it.unicam.loyaltyplatform.programmaFedelta.TipoProgramma;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProgrammaFedeltaDTO {
    TipoProgramma tipo;
    Long aziendaId;
    String nome;
}
