package it.unicam.loyaltyplatform.azienda;

import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AziendaService {

    private final AziendaRepository aziendaRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    @GetMapping
    public Azienda findAziendaById(Long id) throws RecordNotFoundException{
        Optional<Azienda> azienda = aziendaRepository.findById(id);
        if(azienda.isPresent()) return azienda.get();
        else throw new RecordNotFoundException();
    }

    @GetMapping
    public List<Azienda> getAziende() {
        return aziendaRepository.findAll();
    }

    @PostMapping
    public void registraAzienda(Azienda newAzienda) throws RecordAlreadyExistsException {
        Optional<Azienda> aziendaOptional = aziendaRepository
                .findAziendaByEmail(newAzienda.getEmail());
        if(aziendaOptional.isPresent()) {
            throw new RecordAlreadyExistsException();
        }
        aziendaRepository.save(newAzienda);
    }

    public void aggiungiProgrammaAlCatalogo(Azienda azienda, ProgrammaFedelta programmaFedelta) {
        azienda.getProgrammiFedelta().add(programmaFedelta);
        aziendaRepository.save(azienda);
    }

    @Transactional
    public void modificaAzienda(Long id, String nome, String email) throws RecordAlreadyExistsException{
        Azienda azienda = aziendaRepository.getReferenceById(id);

        if (email != null && !email.isEmpty()) {
            Optional<Azienda> aziendaOptional = aziendaRepository.findAziendaByEmail(email);
            if(aziendaOptional.isPresent()) {
                throw new RecordAlreadyExistsException();
            } else azienda.setEmail(email);
        }

        if (nome != null && !nome.isEmpty()) {
            azienda.setNome(nome);
        }

    }

    @DeleteMapping
    public void cancellaAzienda(Long id) throws RecordNotFoundException{
        boolean exists = aziendaRepository.existsById(id);
        if(!exists) {
            throw new RecordNotFoundException();
        }
        aziendaRepository.deleteById(id);
    }

}
