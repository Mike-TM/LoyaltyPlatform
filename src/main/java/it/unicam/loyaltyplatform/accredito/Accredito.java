package it.unicam.loyaltyplatform.accredito;

import java.util.Date;
import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.tessera.Tessera;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;


@Entity @Table
@NoArgsConstructor(force = true)
public class Accredito {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "id_tessera",
            referencedColumnName = "id_tessera",
            nullable = false,
            updatable = false)
    private final Tessera tessera;

    @ManyToOne
    @JoinColumn(
            name = "id_azienda",
            referencedColumnName = "id_azienda",
            nullable = false,
            updatable = false
    )
    private final Azienda azienda;

    @Column(name = "data", nullable = false)
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
