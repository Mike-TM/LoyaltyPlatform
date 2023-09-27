

package it.unicam.loyaltyplatform.accredito;

import it.unicam.loyaltyplatform.azienda.AziendaController;
import it.unicam.loyaltyplatform.dtos.AccreditoDTO;
import it.unicam.loyaltyplatform.tessera.TesseraController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/accredito")
public class AccreditoController {

    private final AccreditoService accreditoService;

    @Autowired
    public AccreditoController(AccreditoService accreditoService) {
        this.accreditoService = accreditoService;

    }

    @GetMapping
    public List<Accredito> getAccrediti(){
        return accreditoService.getAccrediti();
    }

    @GetMapping(path = "/{idtessera}")
    public List<Accredito> getAccreditiByTessera(@PathVariable Long id){
        return getAccrediti().stream()
                .filter(a -> a.getTessera().getTesseraId().equals(id))
                .toList();
    }

    @PostMapping
    public void registraNuovoAccredito(@RequestBody AccreditoDTO accreditoDto) throws Exception{
        accreditoService.aggiungiAccredito(accreditoDto.getAziendaId(), accreditoDto.getTesseraId(), accreditoDto.getSommaAcquisto());
    }

}


