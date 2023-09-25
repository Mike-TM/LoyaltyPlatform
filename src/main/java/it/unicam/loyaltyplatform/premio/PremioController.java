package it.unicam.loyaltyplatform.premio;

import it.unicam.loyaltyplatform.dtos.PremioDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/premio")
public class PremioController {

    private final PremioService premioService;

    @Autowired
    public PremioController(PremioService premioService) {
        this.premioService = premioService;
    }

    @GetMapping
    public List<Premio> getAllPremi() {
        return this.premioService.getAllPremi();
    }

    @GetMapping(path = "/{id_premio}")
    public Premio getPremioById(@PathVariable Long id) throws RecordNotFoundException {
        return this.premioService.findPremioByID(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Creazione premio avvenuta correttamente.")
    public void registraPremio(@RequestBody PremioDTO premioDTO) throws RecordNotFoundException {
        this.premioService.registraPremio(premioDTO);
    }

    @DeleteMapping(path = "/{id_premio}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Premio cancellato.")
    public void cancellaPremio(@PathVariable("id_premio") Long id) throws RecordNotFoundException{

    }
}
