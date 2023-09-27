package it.unicam.loyaltyplatform.premio;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.dtos.PremioDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;

import it.unicam.loyaltyplatform.livello.Livello;
import it.unicam.loyaltyplatform.livello.LivelloService;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaLivelli;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Service
public class PremioService {

    private final PremioRepository premioRepository;
    private final ProgrammaFedeltaService programmaFedeltaService;
    private final LivelloService livelloService;

    @Autowired
    public PremioService(PremioRepository premioRepository, ProgrammaFedeltaService programmaFedeltaService, LivelloService livelloService) {
        this.premioRepository = premioRepository;
        this.programmaFedeltaService = programmaFedeltaService;
        this.livelloService = livelloService;
    }

    @GetMapping
    public List<Premio> getAllPremi() {
        return this.premioRepository.findAll();
    }

    @GetMapping
    public Premio findPremioByID(Long id) throws RecordNotFoundException{
        Optional<Premio> premio = premioRepository.findById(id);
        if(premio.isPresent()) return premio.get();
        else throw new IllegalStateException("Non esiste un premio con questo ID");
    }

    @PostMapping
    public void aggiungiPremioLivello(PremioDTO dto) throws RecordNotFoundException{
        ProgrammaFedelta programma = this.programmaFedeltaService.findProgrammaByID(dto.getProgrammId());
        if(programma instanceof ProgrammaLivelli programmaLivelli){
            if(programmaLivelli.getLivelli().size() >= dto.getNumLivello() + 1) {
                Livello livello = programmaLivelli.getLivelli().get(dto.getNumLivello());
                Premio premio = new Premio(livello, dto.getNome(), dto.getDescrizione());
                this.livelloService.aggiungiPremio(livello, premio);
            }
        }
    }

    @Transactional
    public void modificaPremio(Long id, String nome, String desc){

    }

}
