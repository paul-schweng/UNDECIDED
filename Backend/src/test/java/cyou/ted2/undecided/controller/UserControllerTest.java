package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.FollowerRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Autowired
    private UserRepository userRepository;
    @Mock
    private FollowerRepository followerRepositoryMock;

    private UserController underTest;

    @BeforeEach
    void setUp(){
        underTest = new UserController(userRepository, followerRepositoryMock);
        var user = new User("","test", "test@test.de");
        userRepository.save(user);
    }

    @Test
    void postNewEmail() {

        //given
        String newEmail = "test123@test.de";
        var user = userRepository.findUserByEmail("test@test.de");

        //when
        underTest.postNewEmail("test@test.de", newEmail);
        var user2 = userRepository.findUserByEmail(newEmail);


        //then
        assertEquals(user.getId(), user2.getId());

    }

    //TODO this test fails add check whether changed email already exists
    @Test
    @Disabled
    void changeEmailToAnEmailThatAlreadyExists() {

        //given
        String newEmail = "test@test.de";

        var user1 = new User("","test123", "test123@test.de");

        userRepository.save(user1);

        //when
        underTest.postNewEmail("test123@test.de", newEmail);

        //then
        //TODO hier muss dann irgendwo error geworfen werden

        var user2 = userRepository.findUserByEmail(newEmail);

        assertEquals(user1.getId(), user2.getId());


    }
}