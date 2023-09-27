package it.unicam.loyaltyplatform.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unicam.loyaltyplatform.programmaFedelta.TipoProgramma;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class ProgrammaFedeltaDTO {
    TipoProgramma tipo;
    Long aziendaId;
    String nome;
}
