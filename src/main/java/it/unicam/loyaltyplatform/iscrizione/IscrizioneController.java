package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.dtos.IscrizioneDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.premio.Premio;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaLivelli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(path = "/tessera/{tesseraId}")
    public List<Iscrizione> getIscrizioniByIdTessera(@PathVariable("tesseraId") long id){
        return getAllIscrizioni().stream()
                .filter(i ->i.getTessera().getTesseraId().equals(id))
                .toList();
    }

    @GetMapping(path = "/{iscrizioneId}")
    public Iscrizione getIscrizioneById(@PathVariable("iscrizioneId") Long id) throws Exception {
        return iscrizioneService.findIscrizioneByID(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Iscrizione al programma fedeltà avvenuta correttamente.")
    public void registraIscrizione(@RequestBody IscrizioneDTO iscrizioneDTO) throws RecordNotFoundException {
        iscrizioneService.registraIscrizione(
                iscrizioneDTO.getIdProgramma(),
                iscrizioneDTO.getIdTessera()
        );
    }

    @GetMapping(path = "/vantaggi/{iscrizioneId}")
    public List<Premio> visualizzaVantaggiProgrammaLivelli(@PathVariable("iscrizioneId") Long iscrizioneId) throws Exception {
        return iscrizioneService.visualizzaVantaggiProgrammaLivelli(iscrizioneId);
    }

    @PutMapping(path = "/riscattapremio/{premioId}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Premio riscattato.")
    public Premio riscattaPremioPfLivelli(@PathVariable("premioId") Long premioId, @RequestParam Long iscrizioneId) throws RecordNotFoundException {
        return iscrizioneService.riscattaPremio(premioId, iscrizioneId);
    }

    @GetMapping(path = "/vantaggidisponibili/{iscrizioneId}")
    public List<Premio> visualizzaPremiRiscattabiliLivelli(@PathVariable("iscrizioneId") Long iscrizioneId) throws RecordNotFoundException {
        return iscrizioneService.premiRiscattabiliLivelli(iscrizioneId);
    }

    @DeleteMapping(path = "/{iscrizioneId}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Cancellazione dell'iscrizione al programma. ")
    public void cancellaIscrizione(@PathVariable("iscrizioneId") Long id) throws RecordNotFoundException{
        iscrizioneService.cancellaIscrizione(id);
    }
}