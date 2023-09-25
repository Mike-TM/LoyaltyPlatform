package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
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

    @DeleteMapping(path = "/{id_programma}")
    @ResponseStatus(value = HttpStatus.OK,
            reason = "Programma fedeltà eliminato.")
    public void cancellaProgrammaFedelta(@PathVariable("programmaId") Long id) throws RecordNotFoundException {
        programmaFedeltaService.cancellaProgrammaFedelta(id);
    }


}