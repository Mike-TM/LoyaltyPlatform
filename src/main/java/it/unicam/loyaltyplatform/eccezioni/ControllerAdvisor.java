package it.unicam.loyaltyplatform.eccezioni;

import it.unicam.loyaltyplatform.cliente.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Cliente> handleClienteNotFound(RecordNotFoundException exc, WebRequest req) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<Cliente> handleClienteAlreadyPresent(RecordAlreadyExistsException exc, WebRequest req){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
