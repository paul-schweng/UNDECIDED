package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authentication, String> {

}
