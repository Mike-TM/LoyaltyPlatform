package it.unicam.loyaltyplatform.accredito;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.tessera.Tessera;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
@Entity(name = "Accredito")
@Table(name = "accredito")
public class Accredito {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name = "id_accredito",
            updatable = false
    )
    private Long idAccredito;

    @Column(
            name = "data",
            nullable = false

    )
    private final Date data;

    @ManyToOne @JsonIgnore
    @JoinColumn(
            name = "id_tessera",
            referencedColumnName = "id_tessera",
            nullable = false,
            updatable = false)
    private final Tessera tessera;

    @ManyToOne @JsonIgnore
    @JoinColumn(
            name = "id_azienda",
            referencedColumnName = "id_azienda",
            nullable = false,
            updatable = false
    )
    private final Azienda azienda;

    private final double spesaAcquisto;

    public Accredito( Tessera tessera, Azienda azienda, Date data, double spesaAcquisto) {
        this.tessera=tessera;
        this.azienda=azienda;
        this.data=data;
        this.spesaAcquisto=spesaAcquisto;
    }
}
