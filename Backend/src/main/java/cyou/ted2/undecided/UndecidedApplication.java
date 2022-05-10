package cyou.ted2.undecided;

import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication( /*exclude = SecurityAutoConfiguration.class */)
@RestController
@CrossOrigin(origins="*", maxAge=3600)
@EnableScheduling
public class UndecidedApplication extends SpringBootServletInitializer {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    public UndecidedApplication(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UndecidedApplication.class, args);
    }


    // every 30min
    @Scheduled(fixedDelay = 30 * 60 * 1000)
    public void updateDatabaseTables() {
        ratingRepository.updateRatingsNum();
        userRepository.updateFollowingNum();
        userRepository.updateFollowerNum();

        System.out.println("!!! \n \nUPDATED DATABASE WITH CRONJOB \n \n!!!");
    }

}