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
    public Azienda findAziendaById(Long id) {
        Optional<Azienda> azienda = aziendaRepository.findById(id);

        return azienda.get();
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
        System.out.print(newAzienda);
    }


    public void aggiungiProgrammaAlCatalogo(Azienda azienda, ProgrammaFedelta programmaFedelta){
        azienda.getProgrammiFedelta().add(programmaFedelta);
    }

    @Transactional
    public void modificaAzienda(Long id, String nome, String email) throws RecordAlreadyExistsException{
        Azienda azienda = aziendaRepository.getById(id);

        if (nome != null && nome.length() > 0) {
            azienda.setNome(nome);
        }

        if (email != null && email.length() > 0) {
            Optional<Azienda> aziendaOptional = aziendaRepository.findAziendaByEmail(email);
            if(aziendaOptional.isPresent()) {
                throw new RecordAlreadyExistsException();
            } else azienda.setEmail(email);
        }

    }

    @DeleteMapping
    public void cancellaAzienda(Long id){
        boolean exists = aziendaRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException(
                    "Non esiste un'azienda con" + id + "come ID");
        }
        aziendaRepository.deleteById(id);
    }

}
