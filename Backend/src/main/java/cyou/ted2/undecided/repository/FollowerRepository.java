package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.Following;
import cyou.ted2.undecided.models.FollowingPK;
import cyou.ted2.undecided.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Following, FollowingPK> {

    @Query(value = "SELECT f.user FROM Following f WHERE f.following.id = ?1 ORDER BY f.followDate")
    List<User> getAllUsingFollowing_Id(String followingID);
}
