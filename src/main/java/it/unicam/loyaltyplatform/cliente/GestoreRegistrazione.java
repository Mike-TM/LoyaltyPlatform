package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.eccezioni.RecordAlreadyExistsException;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.tessera.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Account cliente correttamente registrato.")
    public void registraNuovoCliente(@RequestBody Cliente cliente)
            throws RecordAlreadyExistsException,
            RecordNotFoundException {
        clienteService.aggiungiCliente(cliente);
        tesseraService.aggiungiTessera(cliente.getIdCliente());
    }

    @DeleteMapping(path = "{tesseraId}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Account cliente e relativa tessera cancellati.")
    public void eliminaCliente(@PathVariable("tesseraId") Long tesseraId)
            throws RecordNotFoundException {
        tesseraService.cancellaTessera(tesseraId);
    }
}
