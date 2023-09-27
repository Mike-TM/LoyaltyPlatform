
package it.unicam.loyaltyplatform.tessera;
import it.unicam.loyaltyplatform.dtos.TesseraDTO;
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
    public Tessera getTesseraById(@PathVariable("id") long id) throws Exception {
        return tesseraService.findTesseraById(id);
    }

}



