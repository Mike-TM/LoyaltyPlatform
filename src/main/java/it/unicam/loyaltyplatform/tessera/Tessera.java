package it.unicam.loyaltyplatform.tessera;

import it.unicam.loyaltyplatform.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@ToString
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "tessera")
public class Tessera {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id_tessera",
            updatable = false
    )
    private Long idTessera;

    @OneToOne
    @JoinColumn(
            name = "id_cliente",
            referencedColumnName = "id_cliente"
    )
    private Cliente titolareTessera;

    public Tessera(Cliente cliente){
        this.titolareTessera=cliente;
    }

}

