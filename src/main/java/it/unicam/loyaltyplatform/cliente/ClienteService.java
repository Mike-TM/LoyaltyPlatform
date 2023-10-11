package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.dtos.CredentialsDto;
import it.unicam.loyaltyplatform.dtos.SignUpDto;
import it.unicam.loyaltyplatform.dtos.UserDto;
import it.unicam.loyaltyplatform.eccezioni.AppException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final PasswordEncoder passwordEncoder;

    private final ClienteMapper userMapper;

    public UserDto login(CredentialsDto credentialsDto) {
        Cliente user = clienteRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto register(SignUpDto userDto) {
        Optional<Cliente> optionalUser = clienteRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Cliente user = userMapper.signUpToUser(userDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        Cliente savedUser = clienteRepository.save(user);

        return userMapper.toUserDto(savedUser);
    }

    public UserDto findByLogin(String login) {
        Cliente user = clienteRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }


    /*
        @Autowired
        public ClienteService(ClienteRepository clienteRepository) {
            this.clienteRepository = clienteRepository;
        }

        @GetMapping
        public List<Cliente> getClienti(){
            return clienteRepository.findAll();
        }
    */
    @GetMapping
    public Cliente findClienteById(long id) throws RecordNotFoundException {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else throw new RecordNotFoundException();
    }
}

 /*   @PostMapping
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
    public void modificaCliente(Long id, Cliente clienteDettagli)
            throws RecordNotFoundException, RecordAlreadyExistsException {
        Optional<Cliente> clienteByEmail = this.clienteRepository
                .findClienteByEmail(clienteDettagli.getEmail());
        if(clienteByEmail.isPresent()) {
            throw new RecordAlreadyExistsException();
        }
        if(!clienteDettagli.getEmail().isEmpty()){
            findClienteById(id).setEmail(clienteDettagli.getEmail());
        }
        if(!clienteDettagli.getNome().isEmpty()){
            findClienteById(id).setNome(clienteDettagli.getNome());
        }
        clienteRepository.save(findClienteById(id));
    }

}

 */
