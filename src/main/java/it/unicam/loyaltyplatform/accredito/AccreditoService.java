
package it.unicam.loyaltyplatform.accredito;

import it.unicam.loyaltyplatform.azienda.AziendaController;
import it.unicam.loyaltyplatform.tessera.TesseraController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

//livello di business logic
@Service
public class AccreditoService {

    private final AccreditoRepository accreditoRepository;
    private final AziendaController aziendaController;
    private final TesseraController tesseraController;


    @Autowired
    public AccreditoService(AccreditoRepository accreditoRepository, AziendaController aziendaController, TesseraController tesseraController) {
        this.accreditoRepository = accreditoRepository;
        this.aziendaController=aziendaController;
        this.tesseraController=tesseraController;
    }

    public List<Accredito> getAccrediti() {
        return accreditoRepository.findAll();
    }

    //esempio POST
    @PostMapping
    public void aggiungiAccredito(Long idAzienda, Long idTessera) throws Exception{
        Accredito nuovoAccredito =
                        new Accredito(tesseraController.getTesseraById(idTessera),
                        aziendaController.findAziendaById(idAzienda),
                        new Date());
        accreditoRepository.save(nuovoAccredito);
    }
}

