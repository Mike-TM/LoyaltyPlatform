package it.unicam.loyaltyplatform.premio;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.dtos.PremioDTO;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;

import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedeltaService;
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

    @Autowired
    public PremioService(PremioRepository premioRepository, ProgrammaFedeltaService programmaFedeltaService) {
        this.premioRepository = premioRepository;
        this.programmaFedeltaService = programmaFedeltaService;
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

//    @PostMapping
//    public void registraPremio(PremioDTO dto) throws RecordNotFoundException {
//        ProgrammaFedelta programma = programmaFedeltaService.findProgrammaByID(dto.getProgrammaId());
//        Premio nuovoPremio = new Premio(programma, dto.getNome(), dto.getDescrizione());
//        this.programmaFedeltaService.aggiungiPremio(programma, nuovoPremio);
//        this.premioRepository.save(nuovoPremio);
//    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "Premio aggiornato")
    public void aggiornaPremio(Long idProgramma, Long idTessera) throws RecordNotFoundException{
    }

    @DeleteMapping
    public void cancellaPremio(Long id) throws RecordNotFoundException{
    }
}
