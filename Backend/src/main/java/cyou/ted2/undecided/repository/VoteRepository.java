package cyou.ted2.undecided.repository;


import cyou.ted2.undecided.models.Vote;
import cyou.ted2.undecided.models.VotePK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VotePK> {
}
