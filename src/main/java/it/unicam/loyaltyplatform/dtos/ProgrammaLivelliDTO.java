package it.unicam.loyaltyplatform.dtos;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("livelli")
public class ProgrammaLivelliDTO implements ProgrammaFedeltaDTO {
    private Long aziendaId;
    private String nome;
    private double ratioExpSpesa;
}
