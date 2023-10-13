package it.unicam.loyaltyplatform.repository;

import java.util.Optional;

import it.unicam.loyaltyplatform.models.ERole;
import it.unicam.loyaltyplatform.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}