package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.dtos.PremioDTO;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import it.unicam.loyaltyplatform.livello.Livello;
import it.unicam.loyaltyplatform.premio.Premio;
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

    @PostMapping
    public void registraProgrammaFedelta(@RequestBody ProgrammaFedeltaDTO dto) {
        Azienda azienda = aziendaService.findAziendaById(dto.getAziendaId());
        ProgrammaFedelta nuovoProgramma = programmaFactory.crea(azienda, dto);
        aziendaService.aggiungiProgrammaAlCatalogo(azienda, nuovoProgramma);
        programmaRepository.save(nuovoProgramma);
        System.out.print(nuovoProgramma);
    }

    @Transactional
    public void modificaProgramma(Long id, String nome, Integer ratioExpEuro) throws RecordNotFoundException{
        ProgrammaFedelta programma =this.findProgrammaByID(id);
        //faccio qui le modifiche generali del pf
        if(nome != null && nome.length() > 0){
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

    public void aggiungiLivello(ProgrammaLivelli programma, Livello livello){
        List<Livello> livelliProgramma = programma.getLivelli();
        if(!livelliProgramma.isEmpty()) {
            livelliProgramma.get(livelliProgramma.size() - 1).notUltimo();
        }
        programma.getLivelli().add(livello);
        programmaRepository.save(programma);
    }
}
