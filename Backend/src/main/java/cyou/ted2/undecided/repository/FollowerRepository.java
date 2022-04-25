package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.Following;
import cyou.ted2.undecided.models.FollowingPK;
import cyou.ted2.undecided.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.ZonedDateTime;
import java.util.List;

public interface FollowerRepository extends JpaRepository<Following, FollowingPK> {

    @Query(value = "SELECT f.user FROM Following f WHERE f.following.id = ?1 AND f.followDate < ?2 ORDER BY f.followDate DESC")
    List<User> getAllUsingFollowing_Id(String followingID, ZonedDateTime timestamp, Pageable pageable);

    @Query(value = "SELECT f.following FROM Following f WHERE f.user.id = ?1 AND f.followDate < ?2 ORDER BY f.followDate DESC")
    List<User> getAllUsingFollower_Id(String followingID, ZonedDateTime timestamp, Pageable pageable);

    @Query(value = "SELECT f.user FROM Following f INNER JOIN User u ON u.id = f.user.id LEFT JOIN Following b ON b.user.id = f.following.id and b.following.id = u.id WHERE f.following.id = ?1 AND f.followDate < ?2 ORDER BY f.followDate DESC", nativeQuery = false)
    List<User> getAllFriends(String userId, ZonedDateTime timestamp, Pageable pageable);
}
