package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> getClienti(){
        return clienteService.getClienti();
    }

    @GetMapping(path = "/{id}")
    public Cliente getClienteById(@PathVariable long id) throws Exception {
        return clienteService.findClienteById(id);
    }

    @PutMapping("{id}")
    public void modificaCliente(@PathVariable Long id, @RequestBody Cliente modifiche) throws Exception{
        clienteService.modificaCliente(id, modifiche);
    }

}