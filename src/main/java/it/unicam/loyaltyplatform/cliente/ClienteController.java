package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
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
    public Cliente getClienteById(@PathVariable("id") long id) throws Exception {
        return clienteService.findClienteById(id);
    }

   @PutMapping(path = "{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Dati cliente modificati correttamente.")
    public void modificaCliente(@PathVariable("id") Long id, @RequestBody Cliente modifiche)
            throws RecordAlreadyExistsException, RecordNotFoundException {
        clienteService.modificaCliente(id, modifiche);
    }


}