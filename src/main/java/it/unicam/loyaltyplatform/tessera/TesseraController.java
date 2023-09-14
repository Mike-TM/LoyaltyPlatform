package it.unicam.loyaltyplatform.tessera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    public void registraNuovaTessera(@RequestBody Tessera tessera){
        tesseraService.aggiungiTessera(tessera);
    }
}
