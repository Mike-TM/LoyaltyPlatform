//package it.unicam.loyaltyplatform.premio;
//
//import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@Service
//public class PremioService {
//
//    private final PremioRepository premioRepository;
//
//    @Autowired
//    public PremioService(PremioRepository premioRepository) {
//        this.premioRepository = premioRepository;
//    }
//
//    @GetMapping
//    public List<Premio> getPremi() {
//    }
//
//
//    @GetMapping
//    public Premio findPremioByID(Long id) throws RecordNotFoundException{
//    }
//
//    @PostMapping
//    public void registraPremio(Long programmaId, Long tesseraId) throws RecordNotFoundException {
//    }
//
//    @PutMapping
//    @ResponseStatus(value = HttpStatus.OK, reason = "Premio aggiornato")
//    public void aggiornaPremio(Long idProgramma, Long idTessera) throws RecordNotFoundException{
//    }
//
//    @DeleteMapping
//    public void cancellaPremio(Long id) throws RecordNotFoundException{
//    }
//}
