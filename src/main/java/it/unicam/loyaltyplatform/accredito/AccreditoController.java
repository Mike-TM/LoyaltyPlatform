/*
package it.unicam.loyaltyplatform.accredito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public void registraNuovoAccredito(@RequestBody Accredito accredito){
        accreditoService.aggiungiAccredito(accredito);
    }
}
*/
