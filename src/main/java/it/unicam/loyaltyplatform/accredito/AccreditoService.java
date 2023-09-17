
package it.unicam.loyaltyplatform.accredito;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.dtos.AccreditoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//livello di business logic
@Service
public class AccreditoService {

    private final AccreditoRepository accreditoRepository;

    @Autowired
    public AccreditoService(AccreditoRepository accreditoRepository) {
        this.accreditoRepository = accreditoRepository;
    }

    public List<Accredito> getAccrediti() {
        return accreditoRepository.findAll();
    }

    //esempio POST
    @PostMapping
    public void aggiungiAccredito(Long aziendaId,Long tesseraId) {
        //get tessera by id
        //get azienda by id
        //creo accredito con questi dati e salvo nel repository

    }
}

