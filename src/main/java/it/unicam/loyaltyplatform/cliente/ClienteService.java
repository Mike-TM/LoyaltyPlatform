package it.unicam.loyaltyplatform.cliente;


import it.unicam.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final TesseraService tesseraService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, TesseraService tesseraService) {
        this.clienteRepository = clienteRepository;
        this.tesseraService = tesseraService;
    }


}