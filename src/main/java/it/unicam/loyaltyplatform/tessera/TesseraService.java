
package it.unicam.loyaltyplatform.tessera;

import it.unicam.loyaltyplatform.cliente.Cliente;
import it.unicam.loyaltyplatform.cliente.ClienteService;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TesseraService {

    private final TesseraRepository tesseraRepository;
    private final ClienteService clienteService;

    @Autowired
    public TesseraService(TesseraRepository tesseraRepository, ClienteService clienteService) {
        this.tesseraRepository = tesseraRepository;
        this.clienteService = clienteService;
    }

    public List<Tessera> getTessere() {
        return tesseraRepository.findAll();
    }


    @PostMapping
    public void aggiungiTessera(Long idCliente) throws Exception{
        Cliente cliente = clienteService.getClienteById(idCliente);
        Tessera nuovaTessera = new Tessera(cliente);
        tesseraRepository.save(nuovaTessera);
    }

    public void rimuoviIscrizione(Iscrizione iscrizione){
        iscrizione.getTessera().getIscrizioni().remove(iscrizione);
        tesseraRepository.save(iscrizione.getTessera());
    }

    public Tessera findTesseraById(long id) throws RecordNotFoundException{
        Optional<Tessera> tessera = tesseraRepository.findTesseraByIdTessera(id);
        if(tessera.isPresent()) return tessera.get();
        else throw new RecordNotFoundException();
    }

    public void cancellaTessera(Long id) {
        tesseraRepository.deleteById(id);
    }

}

