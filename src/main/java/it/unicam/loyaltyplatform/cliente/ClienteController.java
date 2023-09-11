package it.unicam.loyaltyplatform.cliente;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {
    public List<Cliente> getClienti() {
        return List.of(
                new Cliente(
                        1L,
                        "Mario",
                        "ciao@test.it"
                )
        );
    }

}
