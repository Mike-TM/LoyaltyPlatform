package it.unicam.loyaltyplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PremioLivelloDTO {
    Long livelloId;
    String nome;
    String descrizione;
}
