
package it.unicam.loyaltyplatform.accredito;

import it.unicam.loyaltyplatform.azienda.AziendaService;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.List;

@Service
public class AccreditoService {

    private final AccreditoRepository accreditoRepository;
    private final AziendaService aziendaService;
    private final TesseraService tesseraService;


    @Autowired
    public AccreditoService(AccreditoRepository accreditoRepository, AziendaService aziendaService, TesseraService tesseraService) {
        this.accreditoRepository = accreditoRepository;
        this.aziendaService = aziendaService;
        this.tesseraService = tesseraService;
    }

    public List<Accredito> getAccrediti() {
        return accreditoRepository.findAll();
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Accredito sul programma fedeltà avvenuto.")
    public void aggiungiAccredito(Long aziendaId, Long tesseraId, double spesa) throws RecordNotFoundException {
        Accredito nuovoAccredito =
                        new Accredito(tesseraService.findTesseraById(tesseraId),
                        aziendaService.findAziendaById(aziendaId),
                        new Date(),spesa);
        accreditoRepository.save(nuovoAccredito);
    }
}

