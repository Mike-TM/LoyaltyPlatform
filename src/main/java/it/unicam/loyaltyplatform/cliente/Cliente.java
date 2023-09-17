package it.unicam.loyaltyplatform.cliente;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
@Entity @Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id_cliente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "nome",
            columnDefinition = "TEXT")
    private String nome;

    @Column(name = "email", columnDefinition = "TEXT")
    private String email;

    public Cliente(String nome, String email){
        this.nome = nome;
        this.email = email;
    }


}
