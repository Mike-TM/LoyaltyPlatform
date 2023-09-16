package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public List<Cliente> getClienti(){
        return clienteRepository.findAll();
    }

    @PostMapping
    public void addNewCliente(Cliente nuovoCliente){
        Optional<Cliente> clienteByEmail = clienteRepository
                .findClienteByEmail(nuovoCliente.getEmail());
        if(clienteByEmail.isPresent()) {
            throw new IllegalStateException("email già registrata");
        }
        clienteRepository.save(nuovoCliente);
        System.out.print(nuovoCliente);
    }

    public Cliente findClienteById(long id) {
        Optional<Cliente> cliente = clienteRepository.findClienteById(id);
        if(cliente.isPresent()) {
            return cliente.get();
        }else throw new IllegalStateException("id cliente non presente");
    }
}
