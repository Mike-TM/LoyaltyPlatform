package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import it.unicam.loyaltyplatform.tessera.Tessera;
import it.unicam.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class IscrizioneService {

    private final IscrizioneRepository iscrizioneRepository;
    private final TesseraService tesseraService;
    private final ProgrammaFedeltaService programmaService;
    private final FactoryIscrizione factoryIscrizione;

    @Autowired
    public IscrizioneService(IscrizioneRepository iscrizioneRepository, FactoryIscrizione factoryIscrizione, TesseraService tesseraService, ProgrammaFedeltaService programmaService) {
        this.iscrizioneRepository = iscrizioneRepository;
        this.tesseraService = tesseraService;
        this.programmaService = programmaService;
        this.factoryIscrizione=factoryIscrizione;
    }

    @GetMapping
    public List<Iscrizione> getIscrizioni() {
        return iscrizioneRepository.findAll();
    }


    @GetMapping
    public Iscrizione findIscrizioneByID(Long id) throws RecordNotFoundException{
        Optional<Iscrizione> iscrizione = iscrizioneRepository.findById(id);
        if(iscrizione.isPresent()) return iscrizione.get();
        else throw new RecordNotFoundException();
    }

    @PostMapping
    public void registraIscrizione(Long programmaId, Long tesseraId) throws RecordNotFoundException {
        Tessera tessera = tesseraService.findTesseraById(tesseraId);
        ProgrammaFedelta programma = programmaService.findProgrammaByID(programmaId);
        Iscrizione newIscrizione = factoryIscrizione.creaIscrizione(programma,tessera);
        programma.getIscrizioni().add(newIscrizione);
        tessera.getIscrizioni().add(newIscrizione);
        iscrizioneRepository.save(newIscrizione);
        System.out.print(newIscrizione);
    }

    @PutMapping@ResponseStatus(value = HttpStatus.OK, reason = "Iscrizione aggiornata")
    public void aggiornaIscrizione(Long idAzienda, Long idTessera) throws RecordNotFoundException{

        List<Iscrizione> daAggiornare = tesseraService.findTesseraById(idTessera).getIscrizioni().stream()
                .filter(i -> i.getProgramma().getAzienda().getAziendaId().equals(idAzienda))
                .toList();
        daAggiornare.stream().
                forEach(i -> this.aggiungiProgresso(i));
    }


    public void aggiungiProgresso(Iscrizione iscrizione){
        if(iscrizione instanceof IscrizioneLivelli) aggiungiEsperienzaLivello((IscrizioneLivelli) iscrizione);
        //if(iscrizione instanceof altroTipoDiIscrizione) doSomething()
    }


    private void aggiungiEsperienzaLivello(IscrizioneLivelli iscrizioneLivelli) {
        //prendo costanti definite nel programma fedeltà a livelli per decidere esperienza da aggiungere e
        //controllare se salire di livello
    }


    @DeleteMapping
    public void cancellaIscrizione(Long id) throws RecordNotFoundException{
        Iscrizione iscrizioneDaCancellare = findIscrizioneByID(id);
        programmaService.rimuoviIscrizione(iscrizioneDaCancellare);
        tesseraService.rimuoviIscrizione(iscrizioneDaCancellare);
        iscrizioneRepository.deleteById(id);
    }
}
