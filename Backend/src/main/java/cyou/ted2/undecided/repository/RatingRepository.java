package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, String> {
    Rating findRatingById(String id);
    List<Rating> findAllByUserId(String userId);


    List<Rating> getRatingsByTimestampIsLessThanAndUserIdOrderByTimestampDesc(ZonedDateTime timestamp, String userId, Pageable pageable);




}

