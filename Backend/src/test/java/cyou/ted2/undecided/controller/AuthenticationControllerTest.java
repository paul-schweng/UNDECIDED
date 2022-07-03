package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.AuthRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

@DataJpaTest
public class AuthenticationControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthRepository authRepository;

    private AuthenticationController underTest;

    @BeforeEach
    public void setUp(){
        underTest = new AuthenticationController(userRepository,authRepository);
    }

    @Test
    public void registerUser() throws NoSuchAlgorithmException, InterruptedException {
        var user = new User("","test","test@test.de");
        user.setPassword("1234");
        underTest.register(user);

        assertEquals("test", userRepository.findUserByEmail("test@test.de").getUsername());
    }

    @Test
    public void registerUserThatExistsUsername() throws NoSuchAlgorithmException, InterruptedException {
        var user = new User("","test", "test@test.de");
        userRepository.save(user);

        var user1 = new User("", "test", "test123@test.de");
        user.setPassword("1234");
        Map<String, Boolean> map = underTest.register(user1);

        assertTrue(map.get("usernameError"));
    }

    @Test
    public void registerUserThatExistsEmail() throws NoSuchAlgorithmException, InterruptedException {
        var user = new User("", "test123","test@test.de");
        userRepository.save(user);

        var user1 = new User("","test", "test@test.de");
        user1.setPassword("1234");
        Map<String, Boolean> map = underTest.register(user1);

        assertTrue(map.get("emailError"));
    }



}
