package it.unicam.loyaltyplatform.cliente;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id_cliente",
            updatable = false
    )
    private Long idCliente;

    @Column(name = "nome", nullable = false)
    @Size(max = 100)
    private String nome;

    @Column(name = "cognome", nullable = false)
    @Size(max = 100)
    private String cognome;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @Column(nullable = false)
    @Size(max = 100)
    private String email;
}