package cyou.ted2.undecided.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name, username,
            email, password, usertype,
            description, profileImage, language;
    private LocalDate birthdate, registerDate;
    private boolean verified, isDarkTheme;
    private int ratingsNum, followerNum, followingNum;
}
