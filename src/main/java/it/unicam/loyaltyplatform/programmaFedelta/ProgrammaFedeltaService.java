package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.dtos.PremioDTO;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
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
    public ProgrammaFedelta findProgrammaByID(Long id){
        Optional<ProgrammaFedelta> programma = programmaRepository.findById(id);
        if(programma.isPresent()) return programma.get();
        else throw new IllegalStateException("Non esiste un programma con questo ID");
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
    public void modificaProgrammaLivelli(Long id, String nome, Integer ratioExpEuro) throws RecordNotFoundException{
        ProgrammaLivelli programma = (ProgrammaLivelli) this.findProgrammaByID(id);

        if(nome!=null && nome.length() > 0) {
            programma.setNome(nome);

        }

        if(ratioExpEuro!=null && ratioExpEuro > 0) {
            programma.setRatioExpEuro(ratioExpEuro);
        }

    }

    @DeleteMapping
    public void cancellaProgrammaFedelta(Long id){
    }

    public void rimuoviIscrizione(Iscrizione iscrizione) {
        iscrizione.getProgramma().getIscrizioni().remove(iscrizione);
        programmaRepository.save(iscrizione.getProgramma());
    }

    public void aggiungiLivello(Long id, String nome, int expLevelUp){
        ProgrammaFedelta programma = this.findProgrammaByID(id);
        if(programma instanceof ProgrammaLivelli programmaLivelli){
            programmaLivelli.getLivelli().add(new Livello(programma, nome, expLevelUp));
            programmaRepository.save(programmaLivelli);
        }
        else System.out.print("Non a livelli, sorry");
    }

    public void aggiungiPremio(Long id, PremioDTO dto, Integer numLivello){
        ProgrammaFedelta programma = this.findProgrammaByID(id);
        if(programma instanceof ProgrammaLivelli programmaLivelli){
            if(numLivello != null && programmaLivelli.getLivelli().get(numLivello) != null){
                Livello livello = programmaLivelli.getLivelli().get(numLivello);
                Premio premio = new Premio(livello, dto.getNome(), dto.getDescrizione());
                livello.getCatalogoPremi().add(premio);
                programmaRepository.save(programmaLivelli);
            }
        }
    }
}
