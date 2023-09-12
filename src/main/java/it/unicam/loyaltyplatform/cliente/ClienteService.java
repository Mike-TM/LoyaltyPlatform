package it.unicam.loyaltyplatform.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public List<Cliente> getClienti(){
        return clienteRepository.findAll();
    }
    public void addNewCliente(Cliente cliente) {
        System.out.print(cliente);
    }

}
