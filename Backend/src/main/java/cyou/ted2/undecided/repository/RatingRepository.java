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

    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() over (ORDER BY r.stars DESC, r.timestamp DESC) AS row_num FROM undecided_autogen.Rating r WHERE userid=?1) t WHERE row_num>?2 LIMIT ?3", nativeQuery = true)
    List<Rating> getBestRatings(String user_id, int rowNum, int limit);


    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() over (ORDER BY r.stars ASC, r.timestamp DESC) AS row_num FROM undecided_autogen.Rating r WHERE userid=?1) t WHERE row_num>?2 LIMIT ?3", nativeQuery = true)
    List<Rating> getWorstRatings(String user_id, int rowNum, int limit);




}

