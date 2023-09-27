package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public Cliente findClienteById(long id) throws RecordNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
            return cliente.get();
        }else throw new RecordNotFoundException();
    }

    @PostMapping
    public void aggiungiCliente(Cliente nuovoCliente) throws RecordAlreadyExistsException{
        Optional<Cliente> clienteByEmail = clienteRepository
                .findClienteByEmail(nuovoCliente.getEmail());
        if(clienteByEmail.isPresent()) {
            throw new RecordAlreadyExistsException();
        }
        clienteRepository.save(nuovoCliente);
        System.out.print(nuovoCliente);
    }

    @Transactional
    public void modificaCliente(Long id, Cliente clienteDettagli) throws RecordNotFoundException {
        if(!clienteDettagli.getEmail().isEmpty()){
            findClienteById(id).setEmail(clienteDettagli.getEmail());
        }
        if(!clienteDettagli.getNome().isEmpty()){
            findClienteById(id).setNome(clienteDettagli.getNome());
        }
        clienteRepository.save(findClienteById(id));
    }

}
