package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
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

    @PostMapping
    public ResponseEntity<Cliente> addNewCliente(Cliente nuovoCliente) throws RecordAlreadyExistsException{
        Optional<Cliente> clienteByEmail = clienteRepository
                .findClienteByEmail(nuovoCliente.getEmail());
        if(clienteByEmail.isPresent()) {
            throw new RecordAlreadyExistsException();
        }
        clienteRepository.save(nuovoCliente);
        System.out.print(nuovoCliente);
        return new ResponseEntity<>(nuovoCliente, HttpStatus.CREATED);
    }

    public Cliente getClienteById(long id) throws RecordNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
            return cliente.get();
        }else throw new RecordNotFoundException();
    }

    public void cancellaCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public void modificaCliente(Long id, Cliente clienteDettagli) throws RecordNotFoundException {
        if(!clienteDettagli.getEmail().isEmpty()){
            getClienteById(id).setEmail(clienteDettagli.getEmail());
        }
        if(!clienteDettagli.getNome().isEmpty()){
            getClienteById(id).setNome(clienteDettagli.getNome());
        }
        clienteRepository.save(getClienteById(id));
    }

}
