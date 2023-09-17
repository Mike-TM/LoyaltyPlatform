package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaRepository;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProgrammaFedelta> getProgrammaFedelta() {
        return programmaFedeltaService.getProgrammaFedelta();
    }

    @PostMapping
    public void registraProgrammaFedelta(@RequestBody ProgrammaFedelta programmaFedelta){
        programmaFedeltaService.registraProgrammaFedelta(programmaFedelta);
    }

    @DeleteMapping(path = "{id_programmaFedelta}")
    public void cancellaProgrammaFedelta(@PathVariable("id_programmaFedelta") Long id){
        programmaFedeltaService.cancellaProgrammaFedelta(id);
    }

}