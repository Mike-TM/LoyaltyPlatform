
package it.unicam.loyaltyplatform.accredito;

import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.tessera.TesseraController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;

//livello di business logic
@Service
public class AccreditoService {

    private final AccreditoRepository accreditoRepository;
    private final AziendaService aziendaService;
    private final TesseraController tesseraController;


    @Autowired
    public AccreditoService(AccreditoRepository accreditoRepository, AziendaService aziendaService, TesseraController tesseraController) {
        this.accreditoRepository = accreditoRepository;
        this.aziendaService=aziendaService;
        this.tesseraController=tesseraController;
    }

    public List<Accredito> getAccrediti() {
        return accreditoRepository.findAll();
    }


    @PostMapping@ResponseStatus(value = HttpStatus.CREATED, reason = "Accredito sul programma fedeltà avvenuto.")
    public void aggiungiAccredito(Long idAzienda, Long idTessera) throws Exception{
        Accredito nuovoAccredito =
                        new Accredito(tesseraController.getTesseraById(idTessera),
                        aziendaService.findAziendaById(idAzienda),
                        new Date());
        accreditoRepository.save(nuovoAccredito);
    }
}

