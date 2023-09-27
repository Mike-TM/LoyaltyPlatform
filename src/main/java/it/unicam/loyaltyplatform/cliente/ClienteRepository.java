package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.azienda.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    Optional<Cliente> findClienteByEmail(String email);

}
