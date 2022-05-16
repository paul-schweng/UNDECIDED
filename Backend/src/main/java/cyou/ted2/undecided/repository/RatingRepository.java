package cyou.ted2.undecided.repository;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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

    @Query(value = "SELECT * FROM (SELECT *, ROW_NUMBER() over (ORDER BY r.voteNum DESC, r.timestamp DESC) AS row_num FROM undecided_autogen.Rating r WHERE userid=?1) t WHERE row_num>?2 LIMIT ?3", nativeQuery = true)
    List<Rating> getMostLikedRatings(String user_id, int rowNum, int limit);

    @Query(value = "SELECT * FROM Rating r WHERE userid in (SELECT followingID FROM Following WHERE userID = ?2) AND r.timestamp<?1 ORDER BY r.timestamp DESC", nativeQuery = true)
    List<Rating> getRatingsOfFollowers(ZonedDateTime timestamp, String userId, Pageable pageable);


    // get ratings by exact match of brand/name -> otherwise get ratings that contain the query string (ordered by likes)
    @Query(value = "SELECT * FROM (SELECT p.name,p.brand,r.*, ROW_NUMBER() over (ORDER BY CASE WHEN (p.name like ?1 OR p.brand like ?1) THEN 0 ELSE 1 END, r.voteNum DESC ) as row_num FROM Rating r, Product p WHERE p.productid = r.productid AND (p.name like CONCAT('%', ?1, '%') OR p.brand like CONCAT('%', ?1, '%')) ) as a WHERE row_num > ?2 LIMIT ?3", nativeQuery = true)
    List<Rating> getRatingsByQuerySearch(String query, int rowNum, int limit);



    @Modifying
    @Transactional
    @Query(value = "UPDATE User u JOIN (SELECT COUNT(*) as ratingsNum, userid FROM Rating GROUP BY userid) t2 ON u.userid = t2.userid SET u.ratingsNum = t2.ratingsNum WHERE u.userid = t2.userid", nativeQuery = true)
    void updateRatingsNum();

}

