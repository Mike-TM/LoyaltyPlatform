


package it.unicam.loyaltyplatform.accredito;

import it.unicam.loyaltyplatform.dtos.AccreditoDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping(path = "/{tesseraId}")
    public List<Accredito> getAccreditiByTessera(@PathVariable("tessera") Long id){
        return getAccrediti().stream()
                .filter(a -> a.getTessera().getTesseraId().equals(id))
                .toList();
    }

    @PostMapping(path = "/convalida")
    @ResponseStatus(value = HttpStatus.OK, reason = "Accredito registrato ed iscrizioni aggiornate.")
    public void convalidaAcquisto(@RequestBody AccreditoDTO accreditoDTO) throws RecordNotFoundException {
        accreditoService.aggiungiAccredito(accreditoDTO.getAziendaId(),
                accreditoDTO.getTesseraId(),
                accreditoDTO.getSommaAcquisto());
    }

}



