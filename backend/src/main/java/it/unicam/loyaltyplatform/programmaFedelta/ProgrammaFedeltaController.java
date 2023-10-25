package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/programmaFedelta")
@CrossOrigin(origins = "http://localhost:3000")
public class ProgrammaFedeltaController {

    private final ProgrammaFedeltaService programmaFedeltaService;

    @Autowired
    public ProgrammaFedeltaController(ProgrammaFedeltaService programmaFedeltaService) {
        this.programmaFedeltaService = programmaFedeltaService;
    }

    @GetMapping(path = "/tipi")
    public TipoProgramma[] getAllTipi(){
        TipoProgramma[] tipi = TipoProgramma.values();
        return tipi;
    }

    @GetMapping
    public List<ProgrammaFedelta> getAllProgrammiFedelta() {
        return programmaFedeltaService.getAllProgrammiFedelta();
    }

    @GetMapping(path = "/{programmaId}")
    public ProgrammaFedelta getProgrammaById(@PathVariable("programmaId") Long id) throws RecordNotFoundException {
        return programmaFedeltaService.findProgrammaByID(id);
    }

    @GetMapping(path = "/nome/{nome}")
    public List<ProgrammaFedelta> getProgrammiByNome(@PathVariable("nome") String nome) {
        return programmaFedeltaService.findProgrammiByNome(nome);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED,
            reason = "Programma fedeltà creato correttamente.")
    public void registraProgrammaFedelta(@RequestBody ProgrammaFedeltaDTO dto) throws RecordNotFoundException, RecordAlreadyExistsException{
        programmaFedeltaService.registraProgrammaFedelta(dto);
    }

    @PatchMapping(path = "/{programmaId}")
    @ResponseStatus(value = HttpStatus.OK,
            reason = "Parametri del programma fedeltà modificati correttamente.")
    public void modificaProgramma(@PathVariable("programmaId") Long id,
                                         @RequestParam (required = false) String nome,
                                         @RequestParam (required = false) Integer ratioExpEuro)
            throws RecordNotFoundException, RecordAlreadyExistsException {
        programmaFedeltaService.modificaProgramma(id, nome, ratioExpEuro);
    }

    @DeleteMapping(path = "/{programmaId}")
    @ResponseStatus(value = HttpStatus.OK,
            reason = "Programma fedeltà eliminato.")
    public void cancellaProgrammaFedelta(@PathVariable("programmaId") Long id) throws RecordNotFoundException {
        programmaFedeltaService.cancellaProgrammaFedelta(id);
    }

}