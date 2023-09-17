/*
package it.unicam.loyaltyplatform.accredito;

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
    public void aggiungiAccredito(Accredito accredito) {
        System.out.println(accredito);
    }
}
*/
