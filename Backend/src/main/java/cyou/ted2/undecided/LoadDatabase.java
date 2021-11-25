package cyou.ted2.undecided;
import cyou.ted2.undecided.models.Location;
import cyou.ted2.undecided.models.Product;
import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Collections;
//h2 database configuration for testing if controller works
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(RatingRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Rating("Test", "test", Collections.singletonList("test"), Collections.singletonList(1), Collections.singletonList(new User()), LocalDateTime.now(), 2.0, 1, 1, new Product(), new User(), new Location())));
        };
    }
}
