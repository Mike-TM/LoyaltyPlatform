package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.dtos.PremioDTO;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/programmaFedelta")
public class ProgrammaFedeltaController {

    private final ProgrammaFedeltaService programmaFedeltaService;

    @Autowired
    public ProgrammaFedeltaController(ProgrammaFedeltaService programmaFedeltaService) {
        this.programmaFedeltaService = programmaFedeltaService;
    }

    @GetMapping
    public List<ProgrammaFedelta> getAllProgrammiFedelta() {
        return programmaFedeltaService.getAllProgrammiFedelta();
    }

    @GetMapping(path = "/{programmaId}")
    public ProgrammaFedelta getProgrammaById(@PathVariable Long id) throws Exception {
        return programmaFedeltaService.findProgrammaByID(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED,
            reason = "Programma fedeltà creato correttamente.")
    public void registraProgrammaFedelta(@RequestBody ProgrammaFedeltaDTO dto){
        programmaFedeltaService.registraProgrammaFedelta(dto);
    }

    @PatchMapping(path = "/{programmaId}")
    @ResponseStatus(value = HttpStatus.OK,
            reason = "Parametri del programma fedeltà modificati correttamente.")
    public void modificaProgrammaLivelli(@PathVariable("programmaId") Long id,
                                         @RequestParam (required = false) String nome,
                                         @RequestParam (required = false) Integer ratioExpEuro)
            throws RecordNotFoundException {
        programmaFedeltaService.modificaProgrammaLivelli(id, nome, ratioExpEuro);
    }

    @DeleteMapping(path = "/{programmaId}")
    @ResponseStatus(value = HttpStatus.OK,
            reason = "Programma fedeltà eliminato.")
    public void cancellaProgrammaFedelta(@PathVariable("programmaId") Long id) throws RecordNotFoundException {
        programmaFedeltaService.cancellaProgrammaFedelta(id);
    }

    @PostMapping(path = "/{programmaId}/addLivello")
    @ResponseStatus(value = HttpStatus.CREATED,
            reason = "Livello creato correttamente.")
    public void aggiungiLivello(@PathVariable("programmaId") Long id,
                                @RequestParam String nome,
                                @RequestParam int expLevelUp) throws Exception{
        this.programmaFedeltaService.aggiungiLivello(id, nome, expLevelUp);
    }

    @PostMapping(path = "/{programmaId}/addPremio")
    @ResponseStatus(value = HttpStatus.CREATED,
            reason = "Livello creato correttamente.")
    public void aggiungiPremio(@PathVariable("programmaId") Long id,
                               @RequestBody PremioDTO dto,
                               @RequestParam (required = false) Integer numLivello) throws Exception{

        this.programmaFedeltaService.aggiungiPremio(id, dto, numLivello);
    }

}