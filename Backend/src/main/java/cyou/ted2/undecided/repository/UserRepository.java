package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmail(String email);

    User findUserByUsername(String userName);

    User findUserById(String id);

    List<User> findByUsernameContainingOrNameContaining(String username, String name);

}
