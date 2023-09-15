package it.unicam.loyaltyplatform.azienda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AziendaRepository extends JpaRepository<Azienda,Long> {

    @Query("SELECT a from Azienda a WHERE a.email = ?1")
    Optional<Azienda> findAziendaByEmail(String email);
}
