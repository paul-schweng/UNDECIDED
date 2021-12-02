package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
}
