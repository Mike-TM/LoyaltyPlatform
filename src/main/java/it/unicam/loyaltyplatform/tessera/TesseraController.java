
package it.unicam.loyaltyplatform.tessera;
import it.unicam.loyaltyplatform.dtos.TesseraDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    //esempio GET
    @GetMapping
    public List<Tessera> getTessere(){
        return tesseraService.getTessere();
    }

    @GetMapping(path = "/{id}")
    public Tessera getTesseraById(@PathVariable long id) throws Exception {
        return tesseraService.findTesseraById(id);
    }

    @PostMapping
    public void registraNuovaTessera(@RequestBody TesseraDTO TesseraDto) throws Exception{
        tesseraService.aggiungiTessera(TesseraDto.getClienteId());
    }

    @DeleteMapping("{id}")
    public void cancellaTessera(@PathVariable Long id) throws Exception{
        tesseraService.cancellaTessera(id);
    }
}



