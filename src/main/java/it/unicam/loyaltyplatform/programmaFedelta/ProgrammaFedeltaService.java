package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
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

    private final ProgrammaFedeltaRepository programmaFedeltaRepository;

    @Autowired
    public ProgrammaFedeltaService(ProgrammaFedeltaRepository programmaFedeltaRepository) {
        this.programmaFedeltaRepository = programmaFedeltaRepository;
    }

    @GetMapping
    public List<ProgrammaFedelta> getProgrammaFedelta() {
        return programmaFedeltaRepository.findAll();
    }

    @PostMapping
    public void registraProgrammaFedelta(ProgrammaFedelta newProgrammaFedelta){
        Optional<ProgrammaFedelta> programmaFedeltaOptional = programmaFedeltaRepository
                .findProgrammaFedeltaByAziendaAndNome(newProgrammaFedelta.getAzienda(), newProgrammaFedelta.getNome());
        if(programmaFedeltaOptional.isPresent()) {
            throw new IllegalStateException("Email già registrata");
        }
        programmaFedeltaRepository.save(newProgrammaFedelta);
        System.out.print(newProgrammaFedelta);
    }

    @DeleteMapping
    public void cancellaProgrammaFedelta(Long id){
        boolean exists = programmaFedeltaRepository.existsById(id);
        if(!exists) {
            throw new IllegalStateException(
                    "Non esiste un programma fedeltà con" + id + "come ID");
        }
        programmaFedeltaRepository.deleteById(id);
    }


}
