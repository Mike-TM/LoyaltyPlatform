package it.unicam.loyaltyplatform.azienda;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.loyaltyplatform.accredito.Accredito;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity(name = "Azienda")
@Table(
        name = "azienda",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unica", columnNames = "email")
        }
)
public class Azienda {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(
            name = "id_azienda",
            updatable = false
    )
    private Long id;

    @Column(
            name = "nome",
            nullable = false,
            columnDefinition = "VARCHAR(40)"
    )
    private String nome;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "VARCHAR(320)"
    )
    private String email;

    @OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL)
    private final List<ProgrammaFedelta> programmiFedelta;


    @OneToMany(mappedBy = "azienda", cascade = CascadeType.ALL)
    private final List<Accredito> accrediti;

    /**
     * Costruttore di default
     */
    public Azienda() {
        programmiFedelta = new ArrayList<>();
        accrediti = new ArrayList<>();
    }

    /**
     * Costruttore senza id, il quale verrà generato dal DB
     * @param nome
     * @param email
     */
    public Azienda(String nome, String email) {
        this.nome = nome;
        this.email = email;
        programmiFedelta = new ArrayList<>();
        accrediti = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Azienda{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", programmiFedelta=" + programmiFedelta +
                '}';
    }
}
