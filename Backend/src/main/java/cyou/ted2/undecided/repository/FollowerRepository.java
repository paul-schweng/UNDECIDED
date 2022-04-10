package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.Following;
import cyou.ted2.undecided.models.FollowingPK;
import cyou.ted2.undecided.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Following, FollowingPK> {

}
