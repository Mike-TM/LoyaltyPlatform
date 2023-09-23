package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaFedeltaService {

    private final ProgrammaFedeltaRepository programmaFedeltaRepository;
    private final AziendaService aziendaService;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFedeltaRepository programmaFedeltaRepository, AziendaService aziendaService) {
        this.programmaFedeltaRepository = programmaFedeltaRepository;
        this.aziendaService =aziendaService;
    }

    @GetMapping
    public List<ProgrammaFedelta> getProgrammaFedelta() {
        return programmaFedeltaRepository.findAll();
    }

    @GetMapping
    public ProgrammaFedelta findProgrammaByID(Long id) throws RecordNotFoundException{
        Optional<ProgrammaFedelta> programma = programmaFedeltaRepository.findById(id);
        if(programma.isPresent()) return programma.get();
        else throw new RecordNotFoundException();
    }

    @PostMapping
    public void registraProgrammaFedelta(Long azienda_id, String nome){

        Azienda azienda = aziendaService.findAziendaById(azienda_id);
        ProgrammaFedelta newProgrammaFedelta = new ProgrammaFedelta(azienda, nome);
        aziendaService.aggiungiProgrammaAlCatalogo(azienda, newProgrammaFedelta);
        programmaFedeltaRepository.save(newProgrammaFedelta);
        System.out.print(newProgrammaFedelta);
    }

    public void rimuoviIscrizione(Iscrizione iscrizione){
        iscrizione.getProgramma().getIscrizioni().remove(iscrizione);
        programmaFedeltaRepository.save(iscrizione.getProgramma());
    }

    @DeleteMapping
    public void cancellaProgrammaFedelta(Long id) throws RecordNotFoundException{
        boolean exists = programmaFedeltaRepository.existsById(id);
        if(!exists) {
            throw new RecordNotFoundException();
        }
        programmaFedeltaRepository.deleteById(id);
    }


}
