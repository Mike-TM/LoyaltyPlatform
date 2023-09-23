package it.unicam.loyaltyplatform.azienda;

import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Azienda> getAllAziende() {
        return aziendaService.getAziende();
    }

    @GetMapping(path = "/{id_azienda}")
    public Azienda findAziendaById(@PathVariable Long id) {
        return aziendaService.findAziendaById(id);
    }

    @PostMapping@ResponseStatus(value = HttpStatus.CREATED, reason = "Nuova azienda registrata correttamente.")
    public void registraAzienda(@RequestBody Azienda azienda){
        aziendaService.registraAzienda(azienda);
    }

    @PutMapping(path = "{id_azienda}")@ResponseStatus(value = HttpStatus.OK, reason = "Dati azienda modificati correttamente.")
    public void modificaAzienda(
            @PathVariable("id_azienda") Long id,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email) throws Exception{
        aziendaService.modificaAzienda(id, nome, email);
    }

    @DeleteMapping(path = "{id_azienda}")@ResponseStatus(value = HttpStatus.OK, reason = "Azienda eliminata.")
    public void cancellaAzienda(@PathVariable("id_azienda") Long id){
        aziendaService.cancellaAzienda(id);
    }

}
