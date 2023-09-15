package it.unicam.loyaltyplatform.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/azienda")
public class AziendaController {

    private final AziendaService aziendaService;

    @Autowired
    public AziendaController(AziendaService aziendaService) {
        this.aziendaService = aziendaService;
    }

    @GetMapping
    public List<Azienda> getAziende() {
        return aziendaService.getAziende();
    }

    @PostMapping
    public void registraNuovaAzienda(@RequestBody Azienda azienda){
        aziendaService.addNewAzienda(azienda);
    }
}
