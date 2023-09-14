package it.unicam.loyaltyplatform.tessera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
}
