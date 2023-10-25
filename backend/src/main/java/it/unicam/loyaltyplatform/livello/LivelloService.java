package it.unicam.loyaltyplatform.livello;

import it.unicam.loyaltyplatform.dtos.LivelloDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.premio.Premio;
import it.unicam.loyaltyplatform.programmaFedelta.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public void aggiungiLivello(LivelloDTO dto) throws RecordNotFoundException, RecordAlreadyExistsException {
        ProgrammaFedelta programma = this.programmaService.findProgrammaByID(dto.getProgrammaId());
        if(programma instanceof ProgrammaLivelli programmaLivelli){
            for (Livello l: programmaLivelli.getLivelli()) {
                if(l.getNome().equals(dto.getNome())){
                    throw new RecordAlreadyExistsException();
                }
            }
            Livello nuovoLivello = new Livello(programmaLivelli, dto.getNome(), dto.getExpNextLevel());
            this.programmaService.aggiungiLivello(programmaLivelli, nuovoLivello);
        }
        else return;
    }

    @Transactional
    public void modificaLivello(Long id, String nome, Integer expNextLevel)
            throws RecordNotFoundException, RecordAlreadyExistsException {
        Livello livello = this.findLivelloByID(id);

        if(nome != null && !nome.isEmpty()){
            for (Livello l : livello.getProgramma().getLivelli()) {
                if (l.getNome().equals(nome)){
                    throw new RecordAlreadyExistsException();
                }
            }
            livello.setNome(nome);
        }

        if(expNextLevel != null && expNextLevel > 0){
            livello.setExpLevelUp(expNextLevel);
        }
        this.livelloRepository.save(livello);
    }

    public void aggiungiPremio(Livello livello, Premio premio) throws RecordAlreadyExistsException {
        livello.getCatalogoPremi().add(premio);
        this.livelloRepository.save(livello);
    }
}
