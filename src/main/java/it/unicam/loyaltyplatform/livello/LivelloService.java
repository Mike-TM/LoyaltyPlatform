package it.unicam.loyaltyplatform.livello;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.dtos.LivelloDTO;
import it.unicam.loyaltyplatform.dtos.PremioDTO;
import it.unicam.loyaltyplatform.dtos.ProgrammaFedeltaDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import it.unicam.loyaltyplatform.premio.Premio;
import it.unicam.loyaltyplatform.programmaFedelta.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LivelloService {

    private final LivelloRepository livelloRepository;
    private final ProgrammaFedeltaService programmaService;

    @Autowired
    public LivelloService (LivelloRepository livelloRepository,
            ProgrammaFedeltaService programmaFedeltaService) {
        this.livelloRepository = livelloRepository;
        this.programmaService = programmaFedeltaService;
    }

    @GetMapping
    public List<Livello> getAllLivelli() {
        return livelloRepository.findAll();
    }

    @GetMapping
    public Livello findLivelloByID(Long id) throws RecordNotFoundException{
        Optional<Livello> livello = this.livelloRepository.findById(id);
        if(livello.isPresent()) return livello.get();
        else throw new RecordNotFoundException();
    }

    @PostMapping
    public void aggiungiLivello(LivelloDTO dto) throws RecordNotFoundException{
        ProgrammaFedelta programma = this.programmaService.findProgrammaByID(dto.getProgrammaId());
        if(programma instanceof ProgrammaLivelli programmaLivelli){
            Livello nuovoLivello = new Livello(programmaLivelli, dto.getNome(), dto.getExpNextLevel());
            this.programmaService.aggiungiLivello(programmaLivelli, nuovoLivello);
        }
        else return;
    }

    @Transactional
    public void modificaLivello(Long id, String nome, Integer expNextLevel)
            throws RecordNotFoundException {
        Livello livello = this.findLivelloByID(id);

        if(nome != null && nome.length() > 0){
            livello.setNome(nome);
        }

        if(expNextLevel != null && expNextLevel > 0){
            livello.setExpLevelUp(expNextLevel);
        }
        this.livelloRepository.save(livello);
    }

    public void aggiungiPremio(Livello livello, Premio premio){
        livello.getCatalogoPremi().add(premio);
        this.livelloRepository.save(livello);
    }
}
