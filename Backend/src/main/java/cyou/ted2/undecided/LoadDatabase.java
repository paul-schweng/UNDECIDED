package cyou.ted2.undecided;
import cyou.ted2.undecided.models.*;
import cyou.ted2.undecided.repository.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

//h2 database.json configuration for testing if controller works
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

/*
    @Bean
    CommandLineRunner initDatabase(RatingRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Rating(
                    "Test description",
                    Collections.emptyList(),
                    Collections.singletonList(new Type(1, "test")),
                    Collections.singletonList(1),
                    Collections.singletonList(new User()),
                    LocalDateTime.now(),
                    2.0,
                    1,
                    1,
                    new Product("TestProduct", "TestBrand"),
                    new User(),
                    new Location())));
        };
    }*/


}
