package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@ContextConfiguration(classes = UserRepository.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private RatingRepository rr;
    @Autowired
    private UserRepository underTest;



    @Test
    void findUserByEmail() {
        //given
        String email = "test@test.de";
        User user = new User("123", "test", email);

        underTest.save(user);


        //when
        var user2 = underTest.findUserByEmail(email);

        //then
        assertTrue(user.getEmail() == user2.getEmail());

    }

    @Test
    @Disabled
    void findUserByUsername() {
    }
}