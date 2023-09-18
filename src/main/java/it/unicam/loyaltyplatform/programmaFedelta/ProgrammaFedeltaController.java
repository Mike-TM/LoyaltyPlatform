package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaRepository;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.tessera.Tessera;
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

    @GetMapping(path = "/{id_programma}")
    public ProgrammaFedelta getProgrammaById(@PathVariable Long id) throws Exception {
        return programmaFedeltaService.findProgrammaByID(id);
    }

    @PostMapping
    public void registraProgrammaFedelta(@RequestBody ProgrammaFedeltaDTO programmaFedeltaDTO){
        programmaFedeltaService.registraProgrammaFedelta(
                programmaFedeltaDTO.getAziendaId(),
                programmaFedeltaDTO.getNome()
        );
    }

    @DeleteMapping(path = "/{id_programma}")
    public void cancellaProgrammaFedelta(@PathVariable("id_programmaFedelta") Long id){
        programmaFedeltaService.cancellaProgrammaFedelta(id);
    }


}