package dadefinire.todo;

import it.unicam.loyaltyplatform.cliente.Cliente;
import it.unicam.loyaltyplatform.transazione.Accredito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Tessera {
    static AtomicInteger nextId = new AtomicInteger();
    private final int id;
    private List<Accredito> storicoPunti;
    private Cliente titolareTessera;
    private List<ProgrammaFedelta> pianiSottoscritti;
    public Tessera(Cliente titolareTessera) {
        this.id = nextId.incrementAndGet();
        this.storicoPunti = new ArrayList<>();
        this.titolareTessera = titolareTessera;
        this.pianiSottoscritti = new ArrayList<>();
    }

    public Cliente getTitolareTessera() {
        return titolareTessera;
    }

    public void setTitolareTessera(Cliente nuovoTitolare){
        this.titolareTessera=nuovoTitolare;
    }

    public int getId() {
        return id;
    }


}
