package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Following;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.FollowerRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FollowerRepository followerRepository;

    private UserController underTest;

    User user;

    @BeforeEach
    void setUp(){
        underTest = new UserController(userRepository, followerRepository);
        user = new User("","test", "test@test.de");
        user.setFollowerNum(0);
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

        var user2 = userRepository.findUserByEmail(newEmail);

        assertEquals(user1.getId(), user2.getId());
    }

    @Test
    void getFollowing(){
        PartialLoadFollow partialLoadFollow = new PartialLoadFollow();
        partialLoadFollow.setUserid(userRepository.findUserByEmail("test@test.de").getId());

        var user1 = new User("","test123", "test123@test.de");
        user1.setFollowingNum(0);
        userRepository.save(user1);



        Following follow = new Following();
        user1 = userRepository.findUserByEmail("test123@test.de");
        user = userRepository.findUserByEmail("test@test.de");

        follow.setUser(user1);
        follow.setFollowing(user);
        follow.setFollowDate(ZonedDateTime.now());

        user1.setFollowingNum(user1.getFollowingNum()+1);
        user.setFollowerNum(user.getFollowerNum()+1);

        followerRepository.save(follow);
        userRepository.save(user);
        userRepository.save(user1);

        ResponseEntity re = underTest.getFollowing(partialLoadFollow);
        List<User> list= (List<User>) re.getBody();

    }
}