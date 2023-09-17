package it.unicam.loyaltyplatform.programmaFedelta;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.premio.Premio;
import jakarta.persistence.*;

import java.util.List;

@Entity @Table
public class ProgrammaFedelta {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name ="id_programma")
    private Long idProgramma;

    @Column(name = "num_iscrizioni", nullable = false, scale = 0)
    private int clientiRegistrati;
    @ManyToOne
    @JoinColumn(name = "id_azienda", nullable = false, updatable = false)
    private Azienda azienda;

    @OneToMany(mappedBy = "programmaFedelta")
    private List<Premio> catalogoPremi;

    public ProgrammaFedelta() {
        int clientiRegistrati = 0;
    }

    public ProgrammaFedelta(Long idProgramma, Azienda azienda) {
        this.idProgramma = idProgramma;
        this.clientiRegistrati =  0;
        this.azienda = azienda;
    }

    public ProgrammaFedelta(Azienda azienda) {
        this.clientiRegistrati = 0;
        this.azienda = azienda;
    }
}
