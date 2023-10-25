package it.unicam.loyaltyplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LivelloDTO {
    Long programmaId;
    String nome;
    Integer expNextLevel;
}
