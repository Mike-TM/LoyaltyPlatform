package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.config.UserAuthenticationProvider;
import it.unicam.loyaltyplatform.dtos.CredentialsDto;
import it.unicam.loyaltyplatform.dtos.SignUpDto;
import it.unicam.loyaltyplatform.dtos.UserDto;
import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
import it.unicam.loyaltyplatform.tessera.TesseraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final ClienteService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;
    private final TesseraService tesseraService;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignUpDto user) throws RecordNotFoundException {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        tesseraService.aggiungiTessera(createdUser.getId());
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }
}

