package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.Rating;
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

    @Query(value = "SELECT * FROM (SELECT u.*, ROW_NUMBER() over (ORDER BY CASE WHEN (u.name like ?1 OR u.username like ?1) THEN 0 ELSE 1 END, u.followerNum DESC, u.registerDate DESC) as row_num FROM User u WHERE (u.name like CONCAT('%', ?1, '%') OR u.username like CONCAT('%', ?1, '%')) ) as a WHERE row_num > ?2 LIMIT ?3", nativeQuery = true)
    List<User> getUsersByQuerySearch(String query, int rowNum, int limit);



    @Transactional
    @Modifying
    @Query(value = "UPDATE User u JOIN (SELECT COUNT(*) as followingNum, userID FROM Following GROUP BY userID) t2 ON u.userid = t2.userid SET u.followingNum = t2.followingNum WHERE u.userid = t2.userid", nativeQuery = true)
    void updateFollowingNum();


    @Transactional
    @Modifying
    @Query(value = "UPDATE User u JOIN (SELECT COUNT(*) as followerNum, followingID FROM Following GROUP BY followingID) t2 ON u.userid = t2.followingID SET u.followerNum = t2.followerNum WHERE u.userid = t2.followingID", nativeQuery = true)
    void updateFollowerNum();

}
