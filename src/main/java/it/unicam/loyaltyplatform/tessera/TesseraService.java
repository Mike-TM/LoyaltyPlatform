
package it.unicam.loyaltyplatform.tessera;

import it.unicam.loyaltyplatform.cliente.Cliente;
import it.unicam.loyaltyplatform.cliente.ClienteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TesseraService {

    private final TesseraRepository tesseraRepository;
    private final ClienteController clienteController;

    @Autowired
    public TesseraService(TesseraRepository tesseraRepository, ClienteController clienteController) {
        this.tesseraRepository = tesseraRepository;
        this.clienteController=clienteController;
    }

    public List<Tessera> getTessere() {
        return tesseraRepository.findAll();
    }

    //esempio POST
    @PostMapping
    public void aggiungiTessera(Long idCliente) throws Exception{
        Cliente cliente = clienteController.getClienteById(idCliente);
        Tessera nuovaTessera = new Tessera(cliente);
        tesseraRepository.save(nuovaTessera);
    }

    public Tessera findTesseraById(long id) {
        Optional<Tessera> tessera = tesseraRepository.findTesseraByIdTessera(id);
        if(tessera.isPresent()) return tessera.get();
        else throw new IllegalStateException("Id tessera non presente");
    }

    public void cancellaTessera(Tessera tessera) {
        tesseraRepository.delete(tessera);
    }

}

