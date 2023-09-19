package it.unicam.loyaltyplatform.iscrizione;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import it.unicam.loyaltyplatform.tessera.Tessera;
import it.unicam.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public Iscrizione findIscrizioneByID(Long id){
        Optional<Iscrizione> iscrizione = iscrizioneRepository.findById(id);
        if(iscrizione.isPresent()) return iscrizione.get();
        else throw new IllegalStateException("Id iscrizione non presente");
    }

    @PostMapping
    public void registraIscrizione(Long programmaId, Long tesseraId){

        Tessera tessera = tesseraService.findTesseraById(tesseraId);
        ProgrammaFedelta programma = programmaService.findProgrammaByID(programmaId);
        Iscrizione newIscrizione = new Iscrizione(programma, tessera);
        programma.getIscrizioni().add(newIscrizione);
        tessera.getIscrizioni().add(newIscrizione);
        iscrizioneRepository.save(newIscrizione);
        System.out.print(newIscrizione);
    }

    @DeleteMapping
    public void cancellaIscrizione(Long id){
        boolean exists = iscrizioneRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException(
                    "Non esiste un'iscrizione con ID " + id);
        }
        iscrizioneRepository.deleteById(id);
    }
}
