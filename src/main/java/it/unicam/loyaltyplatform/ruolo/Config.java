package it.unicam.loyaltyplatform.ruolo;

import it.unicam.loyaltyplatform.azienda.Azienda;
import it.unicam.loyaltyplatform.azienda.AziendaRepository;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedelta;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaFedeltaRepository;
import it.unicam.loyaltyplatform.programmaFedelta.ProgrammaLivelli;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    CommandLineRunner sottoscrizioneCommandLineRunner(RoleRepository repository) {
        return args -> {
            repository.save(new Role(ERole.ROLE_USER));
            repository.save(new Role(ERole.ROLE_ADMIN));
            repository.save(new Role(ERole.ROLE_MODERATOR));
        };
    }

    @Bean
    CommandLineRunner aziendapfCommandLineRunner(AziendaRepository repository, ProgrammaFedeltaRepository repositorypf) {
        return args -> {
            Azienda LorisCorp = new Azienda("LorisCorp","sbaraglia@gmail.com");
            repository.save(LorisCorp);
            repositorypf.save(new ProgrammaLivelli(LorisCorp,"Loris SuperFedeltà"));
            Azienda TassoniSRL=new Azienda("Tassoni SRL","cedrataspa@gmail.com");
            repository.save(TassoniSRL);
            repositorypf.save(new ProgrammaLivelli(TassoniSRL,"Cedrata Tassoni 4free"));
            Azienda SanPellegreinoSPA=new Azienda("San Pellegrino s.p.a.", "limonataspa@gmail.com");
            repository.save(SanPellegreinoSPA);
            repositorypf.save(new ProgrammaLivelli(SanPellegreinoSPA,"Aranciata Superiore"));
            Azienda FarmaciaMilesi=new Azienda("Farmacia Milesi", "milesimail@gmail.com");
            repository.save(FarmaciaMilesi);
            repositorypf.save(new ProgrammaLivelli(FarmaciaMilesi,"Farmacia Milesi Loyalty"));
            Azienda PizzeriaDaPasquale=new Azienda("Pizzeria da Pasquale", "pasqualemail@gmail.com");
            repository.save(PizzeriaDaPasquale);
            repositorypf.save(new ProgrammaLivelli(PizzeriaDaPasquale,"Pasquale Family"));
        };
    }


}
