package it.unicam.loyaltyplatform.cliente;

import jakarta.persistence.*;
import lombok.*;

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
            nullable = false
    )
    private Long idCliente;

    @Column(name = "nome",
            columnDefinition = "VARCHAR(40)")
    private String nome;

    @Column(name = "email", columnDefinition = "TEXT")
    private String email;

    public Cliente(String nome, String email){
        this.nome = nome;
        this.email = email;
    }


}
