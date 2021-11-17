package cyou.ted2.undecided.models;


import java.time.LocalDate;

public class User {

    private String name, username,
            email, password, usertype,
            description, profileImage, language;
    private LocalDate birthdate, registerDate;
    private boolean verified, isDarkTheme;
}
