

package it.unicam.loyaltyplatform.tessera;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/tessera")
public class TesseraController {

    private final TesseraService tesseraService;

    @Autowired
    public TesseraController(TesseraService tesseraService) {
        this.tesseraService = tesseraService;
    }

    @GetMapping
    public List<Tessera> getTessere(){
        return tesseraService.getTessere();
    }

    @GetMapping(path = "/{id}")
    public Tessera getTesseraById(@PathVariable("id") Long id) throws Exception {
        return tesseraService.findTesseraById(id);
    }

    @GetMapping(path = "/cliente/{clienteId}")
    public long getTesseraByClienteId(@PathVariable("clienteId") Long id) throws Exception {
        return tesseraService.findTesseraByClienteId(id);
    }

    @GetMapping(path = "/{id}/iscrizioni")
    public List<Iscrizione> getIscrizioni(@PathVariable("id") Long id) throws Exception {
        return getTesseraById(id).getIscrizioni();
    }

}




