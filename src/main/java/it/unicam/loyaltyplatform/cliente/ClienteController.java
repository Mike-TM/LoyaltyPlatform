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
        return clienteService.getClienteById(id);
    }

    @PostMapping
    public void registraNuovoCliente(@RequestBody Cliente cliente) {
        clienteService.addNewCliente(cliente);
    }

    @PutMapping("{id}")
    public void modificaCliente(@PathVariable Long id, @RequestBody Cliente modifiche) throws Exception{
        clienteService.modificaCliente(id, modifiche);
    }

    @DeleteMapping("{id}")
    public void cancellaCliente(@PathVariable Long id) throws Exception{
        clienteService.cancellaCliente(id);
    }

}