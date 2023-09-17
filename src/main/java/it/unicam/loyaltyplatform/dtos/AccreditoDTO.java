package it.unicam.loyaltyplatform.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter @AllArgsConstructor@NoArgsConstructor
public class AccreditoDTO {

    private Long tesseraId;

    private Long aziendaId;

    private Date data;
}
