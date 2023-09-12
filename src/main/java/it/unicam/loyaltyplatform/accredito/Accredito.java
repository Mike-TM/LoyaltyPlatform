package it.unicam.loyaltyplatform.accredito;

import dadefinire.todo.*;
import java.util.Date;
import it.unicam.loyaltyplatform.azienda.Azienda;

public class Accredito {

    private Long id;
    private final Tessera tessera;
    private final Azienda azienda;
    private final Date data;


    public Accredito(Long id, Tessera tessera, Azienda azienda, Date data) {
        this.id=id;
        this.tessera=tessera;
        this.azienda=azienda;
        this.data=data;
    }

    public Accredito( Tessera tessera, Azienda azienda, Date data) {
        this.tessera=tessera;
        this.azienda=azienda;
        this.data=data;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public Date getData() {
        return data;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public Long getId() {
        return id;
    }
}

