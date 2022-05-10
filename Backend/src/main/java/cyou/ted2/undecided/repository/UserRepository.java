package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);

    User findUserByUsername(String userName);

    User findUserById(String id);

    List<User> findByUsernameContainingOrNameContaining(String username, String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u JOIN (SELECT COUNT(*) as followingNum, userID FROM Following GROUP BY userID) t2 ON u.userid = t2.userid SET u.followingNum = t2.followingNum WHERE u.userid = t2.userid", nativeQuery = true)
    void updateFollowingNum();


    @Transactional
    @Modifying
    @Query(value = "UPDATE User u JOIN (SELECT COUNT(*) as followerNum, followingID FROM Following GROUP BY followingID) t2 ON u.userid = t2.followingID SET u.followerNum = t2.followerNum WHERE u.userid = t2.followingID", nativeQuery = true)
    void updateFollowerNum();

}
