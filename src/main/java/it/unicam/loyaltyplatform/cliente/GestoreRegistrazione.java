package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path = "api/registrazione")
public class GestoreRegistrazione {

    private final ClienteService clienteService;
    private final TesseraService tesseraService;

    @Autowired
    public GestoreRegistrazione(ClienteService clienteService, TesseraService tesseraService){
        this.clienteService=clienteService;
        this.tesseraService=tesseraService;
    }

    @PostMapping(path = "/cliente")@ResponseStatus(value = HttpStatus.CREATED, reason = "Account cliente correttamente registrato.")
    public void registraNuovoCliente(@RequestBody Cliente cliente) throws Exception {
        clienteService.addNewCliente(cliente);
        tesseraService.aggiungiTessera(cliente.getIdCliente());
    }
}
