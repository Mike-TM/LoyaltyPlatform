package it.unicam.loyaltyplatform.azienda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda,Long> {

    Optional<Azienda> findAziendaByEmail(String email);

    Optional<Azienda> findAziendaByNome(String nome);
}
