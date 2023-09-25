package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.dtos.IscrizioneDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<Iscrizione> getAllIscrizioni() {
        return iscrizioneService.getIscrizioni();
    }

    @GetMapping(path = "/tessera/{id_tessera}")
    public List<Iscrizione> getIscrizioniByIdTessera(@PathVariable long id){
        return getAllIscrizioni().stream()
                .filter(i ->i.getTessera().getIdTessera().equals(id))
                .toList();
    }

    @GetMapping(path = "/{id_iscrizione}")
    public Iscrizione getIscrizioneById(@PathVariable Long id) throws Exception {
        return iscrizioneService.findIscrizioneByID(id);
    }

    @PostMapping@ResponseStatus(value = HttpStatus.CREATED, reason = "Iscrizione al programma fedeltà avvenuta correttamente.")
    public void registraIscrizione(@RequestBody IscrizioneDTO iscrizioneDTO) throws RecordNotFoundException {
        iscrizioneService.registraIscrizione(
                iscrizioneDTO.getIdProgramma(),
                iscrizioneDTO.getIdTessera()
        );
    }

    @DeleteMapping(path = "/{id_iscrizione}")@ResponseStatus(value = HttpStatus.OK, reason = "Cancellazione dell'iscrizione al programma. ")
    public void cancellaIscrizione(@PathVariable("id_iscrizione") Long id) throws RecordNotFoundException{
        iscrizioneService.cancellaIscrizione(id);
    }
}