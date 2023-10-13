package it.unicam.loyaltyplatform.ruolo;

import it.unicam.loyaltyplatform.ruolo.ERole;
import it.unicam.loyaltyplatform.ruolo.Role;
import it.unicam.loyaltyplatform.ruolo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig {

    @Bean
    CommandLineRunner sottoscrizioneCommandLineRunner(RoleRepository repository) {
        return args -> {
            repository.save(new Role(ERole.ROLE_USER));
            repository.save(new Role(ERole.ROLE_ADMIN));
            repository.save(new Role(ERole.ROLE_MODERATOR));
        };
    }
}
