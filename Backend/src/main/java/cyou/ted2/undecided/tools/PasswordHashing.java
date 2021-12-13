package cyou.ted2.undecided.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

public class PasswordHashing {

    public static String hashPassword(String pw){
        int strength = 10; // work factor of bcrypt
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        return bCryptPasswordEncoder.encode(pw);
    }
}
