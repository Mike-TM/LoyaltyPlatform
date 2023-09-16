
package it.unicam.loyaltyplatform.tessera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TesseraService {

    private final TesseraRepository tesseraRepository;

    @Autowired
    public TesseraService(TesseraRepository tesseraRepository) {
        this.tesseraRepository = tesseraRepository;
    }


    public List<Tessera> getTessere() {
        return tesseraRepository.findAll();
    }

    //esempio POST
    @PostMapping
    public void aggiungiTessera(Tessera tessera) {
        System.out.println(tessera);
    }

    public Tessera findTesseraById(long id) {
        Optional<Tessera> tessera = tesseraRepository.findTesseraById(id);
        if(tessera.isPresent()) return tessera.get();
        else throw new IllegalStateException("Id tessera non presente");
    }
}

