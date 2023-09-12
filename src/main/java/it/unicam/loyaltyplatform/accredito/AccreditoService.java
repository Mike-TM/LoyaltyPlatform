package it.unicam.loyaltyplatform.accredito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
