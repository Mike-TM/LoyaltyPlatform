package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import it.unicam.loyaltyplatform.livello.Livello;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammaFedeltaService {

    private final ProgrammaFactory programmaFactory;
    private final ProgrammaFedeltaRepository programmaRepository;
    private final AziendaService aziendaService;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFactory programmaFactory,
                                   ProgrammaFedeltaRepository programmaRepository,
                                   AziendaService aziendaService) {
        this.programmaFactory = programmaFactory;
        this.programmaRepository = programmaRepository;
        this.aziendaService = aziendaService;
    }

    @GetMapping
    public List<ProgrammaFedelta> getAllProgrammiFedelta() {
        return programmaRepository.findAll();
    }

    @GetMapping
    public ProgrammaFedelta findProgrammaByID(Long id) throws RecordNotFoundException{
        Optional<ProgrammaFedelta> programma = programmaRepository.findById(id);
        if(programma.isPresent()) return programma.get();
        else throw new RecordNotFoundException();
    }

    @GetMapping
    public List<ProgrammaFedelta> findProgrammiByNome(String nome) {
        return this.programmaRepository.findProgrammaFedeltaByNome(nome);
    }


    @PostMapping
    public void registraProgrammaFedelta(@RequestBody ProgrammaFedeltaDTO dto) throws RecordNotFoundException, RecordAlreadyExistsException{
        Azienda azienda = aziendaService.findAziendaById(dto.getAziendaId());
        for (ProgrammaFedelta p: azienda.getProgrammiFedelta()) {
            if(p.getNome().equals(dto.getNome()))
                throw new RecordAlreadyExistsException();
        }
        ProgrammaFedelta nuovoProgramma = programmaFactory.crea(azienda, dto);
        aziendaService.aggiungiProgrammaAlCatalogo(azienda, nuovoProgramma);
        System.out.print(nuovoProgramma);
    }

    @Transactional
    public void modificaProgramma(Long id, String nome, Integer ratioExpEuro) throws RecordNotFoundException, RecordAlreadyExistsException{
        ProgrammaFedelta programma =this.findProgrammaByID(id);
        //faccio qui le modifiche generali del pf
        if(nome != null && nome.length() > 0){
            for (ProgrammaFedelta p: programma.getAzienda().getProgrammiFedelta()) {
                if(p.getNome().equals(nome)) throw new RecordAlreadyExistsException();
            }
            programma.setNome(nome);
        }
        //chiamo metodi specifici per modificare parametri presenti solo in alcuni tipi di programma
        if(programma instanceof ProgrammaLivelli programmaLivelli){
            this.modificaProgrammaLivelli(programmaLivelli, ratioExpEuro);
        }
        //gestisco con altri if programmi di altro tipo
        this.programmaRepository.save(programma);
    }

    @Transactional
    public void modificaProgrammaLivelli(ProgrammaLivelli programma, Integer ratioExpEuro) throws RecordNotFoundException{
        if(ratioExpEuro != null && ratioExpEuro > 0){
            programma.setRatioExpEuro(ratioExpEuro);
        }
    }

    @DeleteMapping
    public void cancellaProgrammaFedelta(Long id) throws RecordNotFoundException{
        ProgrammaFedelta programma = this.findProgrammaByID(id);
        this.programmaRepository.deleteById(id);
    }

    public void rimuoviIscrizione(Iscrizione iscrizione) {
        iscrizione.getProgramma().getIscrizioni().remove(iscrizione);
        programmaRepository.save(iscrizione.getProgramma());
    }

    public void aggiungiLivello(ProgrammaLivelli programma, Livello livello) throws RecordAlreadyExistsException {
        List<Livello> livelliProgramma = programma.getLivelli();
        if(!livelliProgramma.isEmpty()) {
            livelliProgramma.get(livelliProgramma.size() - 1).notUltimo();
        }
        programma.getLivelli().add(livello);
        programmaRepository.save(programma);
    }
}
