package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import it.unicam.loyaltyplatform.tessera.Tessera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaFedeltaService {

    private final ProgrammaFedeltaRepository programmaRepository;
    private final AziendaService aziendaService;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFedeltaRepository programmaRepository, AziendaService aziendaService) {
        this.programmaRepository = programmaRepository;
        this.aziendaService =aziendaService;
    }

    @GetMapping
    public List<ProgrammaFedelta> getAllProgrammiFedelta() {
        return programmaRepository.findAll();
    }

    @GetMapping
    public ProgrammaFedelta findProgrammaByID(Long id){
        Optional<ProgrammaFedelta> programma = programmaRepository.findById(id);
        if(programma.isPresent()) return programma.get();
        else throw new IllegalStateException("Id programma non presente");
    }

    @PostMapping
    public void registraProgrammaFedelta(Long azienda_id, String nome){

        Azienda azienda = aziendaService.findAziendaById(azienda_id);
        ProgrammaFedelta newProgrammaFedelta = new ProgrammaFedelta(azienda, nome);
        aziendaService.aggiungiProgrammaAlCatalogo(azienda, newProgrammaFedelta);
        programmaRepository.save(newProgrammaFedelta);
        System.out.print(newProgrammaFedelta);
    }

    @DeleteMapping
    public void cancellaProgrammaFedelta(Long id){
        boolean exists = programmaRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException(
                    "Non esiste un programma fedeltà con" + id + "come ID");
        }
        programmaRepository.deleteById(id);
    }

    public void rimuoviIscrizione(Iscrizione iscrizione) {
        iscrizione.getProgramma().getIscrizioni().remove(iscrizione);
        programmaRepository.save(iscrizione.getProgramma());
    }
}
