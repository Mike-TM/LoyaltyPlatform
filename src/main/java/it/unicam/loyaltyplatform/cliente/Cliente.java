package it.unicam.loyaltyplatform.cliente;


import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity(name = "Cliente")
@Table(
        name = "cliente",
        uniqueConstraints = {
                @UniqueConstraint(name = "email_unica", columnNames = "email")
        }
)
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id_cliente",
            updatable = false
    )
    private Long idCliente;

    @Column(
            name = "nome",
            columnDefinition = "VARCHAR(40)"
    )
    private String nome;

    @Column(
            name = "email",
            columnDefinition = "TEXT"
    )
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private final List<Iscrizione> iscrizioni;

    public Cliente() {
        iscrizioni = new ArrayList<>();
    }

    /**
     * Costruttore senza id, il quale verrà generato dal DB
     * @param nome
     * @param email
     */
    public Cliente(String nome, String email){
        this.nome = nome;
        this.email = email;
        iscrizioni = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
