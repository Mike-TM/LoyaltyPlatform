package it.unicam.loyaltyplatform.cliente;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public void registraNuovoCliente(@RequestBody Cliente cliente) {
        clienteService.addNewCliente(cliente);
    }

    @PutMapping(path = "/{email}")
    public void modificaEmailCliente(Cliente cliente, String nuovaEmail){
        clienteService.modificaEmailCliente(cliente,nuovaEmail);
    }

    @PutMapping(path = "/{nome}")
    public void modificaNomeCliente(Cliente cliente, String nuovoNome){
        clienteService.modificaNomeCliente(cliente, nuovoNome);
    }

    @DeleteMapping
    public void cancellaCliente(Cliente cliente){
        clienteService.cancellaCliente(cliente);
    }

}