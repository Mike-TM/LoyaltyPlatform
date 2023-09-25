package it.unicam.loyaltyplatform.programmaFedelta;

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

    @GetMapping(path = "/{id_programma}")
    public ProgrammaFedelta getProgrammaById(@PathVariable Long id) throws Exception {
        return programmaFedeltaService.findProgrammaByID(id);
    }

    @PostMapping@ResponseStatus(value = HttpStatus.CREATED, reason = "Programma Fedeltà creato correttamente.")
    public void registraProgrammaFedelta(@RequestBody ProgrammaFedeltaDTO programmaFedeltaDTO){
        programmaFedeltaService.registraProgrammaFedelta(
                programmaFedeltaDTO.getAziendaId(),
                programmaFedeltaDTO.getNome()
        );
    }

    @DeleteMapping(path = "/{id_programma}")@ResponseStatus(value = HttpStatus.OK, reason = "Programma fedeltà eliminato.")
    public void cancellaProgrammaFedelta(@PathVariable("id_programmaFedelta") Long id) throws RecordNotFoundException {
        programmaFedeltaService.cancellaProgrammaFedelta(id);
    }


}