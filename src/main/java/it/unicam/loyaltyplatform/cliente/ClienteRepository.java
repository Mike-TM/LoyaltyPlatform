package it.unicam.loyaltyplatform.cliente;

import java.util.Optional;

import it.unicam.loyaltyplatform.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<Cliente> findByEmail(String email);

    Boolean existsByEmail(String email);
}