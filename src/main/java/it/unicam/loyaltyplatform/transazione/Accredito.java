package it.unicam.loyaltyplatform.transazione;

import java.util.Date;

public class Accredito {
    private final Date data;

    Accredito(Date data){
        this.data=data;
    }
    public Date getData() {
        return data;
    }
}
