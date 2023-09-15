package it.unicam.loyaltyplatform.azienda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class AziendaService {

    private final AziendaRepository aziendaRepository;

    @Autowired
    public AziendaService(AziendaRepository aziendaRepository) {
        this.aziendaRepository = aziendaRepository;
    }

    @GetMapping
    public List<Azienda> getAziende() {
        return aziendaRepository.findAll();
    }

    @PostMapping
    public void addNewAzienda(Azienda nuovaAzienda){
        Optional<Azienda> aziendaByEmail = aziendaRepository
                .findAziendaByEmail(nuovaAzienda.getEmail());
        if(aziendaByEmail.isPresent()) {
            throw new IllegalStateException("email già registrata");
        }
        aziendaRepository.save(nuovaAzienda);
        System.out.print(nuovaAzienda);
    }

}
