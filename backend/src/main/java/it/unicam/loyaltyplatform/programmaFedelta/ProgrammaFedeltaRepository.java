package it.unicam.loyaltyplatform.programmaFedelta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgrammaFedeltaRepository extends JpaRepository<ProgrammaFedelta,Long> {
    public List<ProgrammaFedelta> findProgrammaFedeltaByNome(String nome);
}
