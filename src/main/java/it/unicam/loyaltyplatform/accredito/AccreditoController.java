
package it.unicam.loyaltyplatform.accredito;

import it.unicam.loyaltyplatform.dtos.AccreditoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//livello di gestione delle richieste dall'esterno
@RestController
@RequestMapping(path = "api/accredito")
public class AccreditoController {

    private final AccreditoService accreditoService;

    @Autowired
    public AccreditoController(AccreditoService accreditoService) {
        this.accreditoService = accreditoService;
    }

    //metodo esempio GET
    @GetMapping
    public List<Accredito> getAccrediti(){
        return accreditoService.getAccrediti();
    }

    @PostMapping
    public void registraNuovoAccredito(@RequestBody AccreditoDTO accreditoDto) throws Exception{
        accreditoService.aggiungiAccredito(accreditoDto.getAziendaId(), accreditoDto.getTesseraId());
    }

}

