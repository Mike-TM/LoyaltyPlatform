package it.unicam.loyaltyplatform.accredito;



import it.unicam.loyaltyplatform.dtos.AccreditoDTO;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import it.unicam.loyaltyplatform.iscrizione.IscrizioneLivelli;
import it.unicam.loyaltyplatform.iscrizione.IscrizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path = "api/convalida")
public class GestoreConvalida {

    private final IscrizioneService iscrizioneService;
    private final AccreditoService accreditoService;

    @Autowired
    public GestoreConvalida(AccreditoService accreditoService, IscrizioneService iscrizioneService){
        this.accreditoService=accreditoService;
        this.iscrizioneService=iscrizioneService;
    }

    @PostMapping@ResponseStatus(value = HttpStatus.OK, reason = "Convalida confermata.")
    public void convalidaAcquisto(AccreditoDTO accreditoDTO) throws Exception{
        accreditoService.aggiungiAccredito(accreditoDTO.getAziendaId(), accreditoDTO.getTesseraId(), accreditoDTO.getSommaAcquisto());
        iscrizioneService.aggiornaIscrizione(accreditoDTO.getAziendaId(), accreditoDTO.getTesseraId(), accreditoDTO.getSommaAcquisto());
    }



}
