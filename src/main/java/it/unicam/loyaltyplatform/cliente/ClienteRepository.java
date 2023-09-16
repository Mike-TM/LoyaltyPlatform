package it.unicam.loyaltyplatform.cliente;

import it.unicam.loyaltyplatform.azienda.Azienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    @Query("SELECT a from Cliente a WHERE a.email = ?1")
    Optional<Cliente> findClienteByEmail(String email);

    Optional<Cliente> findClienteById(Long id);
}
