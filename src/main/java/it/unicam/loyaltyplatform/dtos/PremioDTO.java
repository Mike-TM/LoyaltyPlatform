package it.unicam.loyaltyplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PremioDTO {
    Long programmId;
    int numLivello;
    String nome;
    String descrizione;
}
