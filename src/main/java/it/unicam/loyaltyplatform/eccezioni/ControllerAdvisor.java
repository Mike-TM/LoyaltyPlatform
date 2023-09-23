package it.unicam.loyaltyplatform.eccezioni;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Record non trovato.")
    public void handleClienteNotFound(RecordNotFoundException exc, WebRequest req) {
    }

    @ExceptionHandler(RecordAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Record Già presente.")
    public void handleClienteAlreadyPresent(RecordAlreadyExistsException exc, WebRequest req){
    }
}
