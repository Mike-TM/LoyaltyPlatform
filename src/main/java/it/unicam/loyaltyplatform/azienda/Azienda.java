package it.unicam.loyaltyplatform.azienda;

import it.unicam.loyaltyplatform.accredito.Accredito;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
@Entity @Table
public class Azienda {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_azienda")
    private Long id;
    @Column(name = "nome_azienda", nullable = false)
    private String name;
    @Column(name = "email_azienda", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "azienda")
    private List<ProgrammaFedelta> programmiFedelta;

    @OneToMany(mappedBy = "azienda")
    private List<Accredito> accrediti;

    public Azienda(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Azienda(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
