package it.unicam.loyaltyplatform.tessera;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TesseraRepository extends JpaRepository<Tessera,Long> {

    //anche qui metodo optional per query
}
