package it.unicam.loyaltyplatform.accredito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditoRepository extends JpaRepository<Accredito,Long> {

}
