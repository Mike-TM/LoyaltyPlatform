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

    @Autowired
    public IscrizioneService(IscrizioneRepository iscrizioneRepository, TesseraService tesseraService, ProgrammaFedeltaService programmaService) {
        this.iscrizioneRepository = iscrizioneRepository;
        this.tesseraService = tesseraService;
        this.programmaService = programmaService;
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
        Iscrizione newIscrizione = new Iscrizione(programma, tessera);
        programma.getIscrizioni().add(newIscrizione);
        tessera.getIscrizioni().add(newIscrizione);
        iscrizioneRepository.save(newIscrizione);
        System.out.print(newIscrizione);
    }

    @PutMapping@ResponseStatus(value = HttpStatus.OK, reason = "Iscrizione aggiornata")
    public void aggiornaIscrizione(Long idAzienda, Long idTessera) throws RecordNotFoundException{

        iscrizioneRepository.saveAll(tesseraService.findTesseraById(idTessera).getIscrizioni().stream()
                .filter(i -> i.getProgramma().getAzienda().getAziendaId().equals(idAzienda))
                .toList());
        //.forEach aggiorna progresso e poi save

    }

    @DeleteMapping
    public void cancellaIscrizione(Long id) throws RecordNotFoundException{
        Iscrizione iscrizioneDaCancellare = findIscrizioneByID(id);
        programmaService.rimuoviIscrizione(iscrizioneDaCancellare);
        tesseraService.rimuoviIscrizione(iscrizioneDaCancellare);
        iscrizioneRepository.deleteById(id);
    }
}
