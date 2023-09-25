package it.unicam.loyaltyplatform.premio;

import it.unicam.loyaltyplatform.iscrizione.Iscrizione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremioRepository extends JpaRepository<Premio, Long> {

}
