package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.dtos.IscrizioneDTO;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/iscrizione")
public class IscrizioneController {

    private final IscrizioneService iscrizioneService;

    @Autowired
    public IscrizioneController(IscrizioneService iscrizioneService) {
        this.iscrizioneService = iscrizioneService;
    }

    @GetMapping
    public List<Iscrizione> getIscrizioni() {
        return iscrizioneService.getIscrizioni();
    }

    @GetMapping(path = "/{id_iscrizione}")
    public Iscrizione getIscrizioneById(@PathVariable Long id) throws Exception {
        return iscrizioneService.findIscrizioneByID(id);
    }

    @PostMapping
    public void registraIscrizione(@RequestBody IscrizioneDTO iscrizioneDTO) throws RecordNotFoundException {
        iscrizioneService.registraIscrizione(
                iscrizioneDTO.getIdProgramma(),
                iscrizioneDTO.getIdTessera()
        );
    }

    @DeleteMapping(path = "/{id_iscrizione}")
    public void cancellaIscrizione(@PathVariable("id_iscrizione") Long id){
        iscrizioneService.cancellaIscrizione(id);
    }
}