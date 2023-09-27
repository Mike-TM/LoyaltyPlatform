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

    @GetMapping(path = "/tessera/{id_tessera}")
    public List<Iscrizione> getIscrizioniByIdTessera(@PathVariable long id){
        return getAllIscrizioni().stream()
                .filter(i ->i.getTessera().getTesseraId().equals(id))
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

    @GetMapping(path = "/vantaggi/{id_iscrizione}")
    public List<Premio> visualizzaVantaggiProgrammaLivelli(@PathVariable Long idIscrizione) throws Exception {
        return iscrizioneService.visualizzaVantaggiProgrammaLivelli(idIscrizione);
    }

    @PutMapping(path = "/riscattapremio/{idpremio}")
    public Premio riscattaPremioPfLivelli(@PathVariable Long idPremio, Long idIscrizione) throws RecordNotFoundException {
        return iscrizioneService.riscattaPremio(idPremio,idIscrizione);
    }

    @GetMapping(path = "/vantaggidisponibili/{id_iscrizione}")
    public List<Premio> visualizzaPremiRiscattabiliLivelli(@PathVariable Long idIscrizione) throws RecordNotFoundException {
        return iscrizioneService.premiRiscattabiliLivelli(idIscrizione);
    }

    @DeleteMapping(path = "/{id_iscrizione}")@ResponseStatus(value = HttpStatus.OK, reason = "Cancellazione dell'iscrizione al programma. ")
    public void cancellaIscrizione(@PathVariable("id_iscrizione") Long id) throws RecordNotFoundException{
        iscrizioneService.cancellaIscrizione(id);
    }
}