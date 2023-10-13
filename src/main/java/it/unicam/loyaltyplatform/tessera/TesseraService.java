//
//package it.unicam.loyaltyplatform.tessera;
//
//import it.unicam.loyaltyplatform.cliente.Cliente;
//import it.unicam.loyaltyplatform.cliente.ClienteService;
//import it.unicam.loyaltyplatform.eccezioni.RecordNotFoundException;
//import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TesseraService {
//
//    private final TesseraRepository tesseraRepository;
//    private final ClienteService clienteService;
//
//    @Autowired
//    public TesseraService(TesseraRepository tesseraRepository, ClienteService clienteService) {
//        this.tesseraRepository = tesseraRepository;
//        this.clienteService = clienteService;
//    }
//
//    @GetMapping
//    public List<Tessera> getTessere() {
//        return tesseraRepository.findAll();
//    }
//
//
//    @PostMapping
//    public void aggiungiTessera(Long clienteId) throws RecordNotFoundException{
//        Cliente cliente = clienteService.findClienteById(clienteId);
//        Tessera nuovaTessera = new Tessera(cliente);
//        tesseraRepository.save(nuovaTessera);
//    }
//
//    public void rimuoviIscrizione(Iscrizione iscrizione){
//        iscrizione.getTessera().getIscrizioni().remove(iscrizione);
//        tesseraRepository.save(iscrizione.getTessera());
//    }
//
//    public Tessera findTesseraById(long id) throws RecordNotFoundException{
//        Optional<Tessera> tessera = tesseraRepository.findById(id);
//        if(tessera.isPresent()) return tessera.get();
//        else throw new RecordNotFoundException();
//    }
//
//    public void cancellaTessera(Long id) throws RecordNotFoundException {
//        Tessera tessera = this.findTesseraById(id);
//        this.tesseraRepository.delete(tessera);
//    }
//
//}
//
