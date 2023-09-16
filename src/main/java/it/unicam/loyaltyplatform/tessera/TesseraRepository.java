
package it.unicam.loyaltyplatform.tessera;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TesseraRepository extends JpaRepository<Tessera,Long> {

    Optional<Tessera> findTesseraById(long id);


}

