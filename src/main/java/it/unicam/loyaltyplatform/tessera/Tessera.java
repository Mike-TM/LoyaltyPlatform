package it.unicam.loyaltyplatform.tessera;

import it.unicam.loyaltyplatform.cliente.Cliente;
import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@Entity @Table(name = "tessera")
public class Tessera {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id_tessera",
            updatable = false
    )
    private Long tesseraId;

    @OneToOne
    @JoinColumn(
            name = "id_cliente",
            referencedColumnName = "id_cliente"
    )
    private Cliente titolareTessera;

    @OneToMany(mappedBy = "tessera", cascade = CascadeType.ALL)
    private final List<Iscrizione> iscrizioni;

    public Tessera() {
        this.iscrizioni = new ArrayList<>();
    }

    public Tessera(Cliente cliente){
        this.titolareTessera=cliente;
        this.iscrizioni = new ArrayList<>();
    }

}

