package cyou.ted2.undecided.services;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.repository.RatingRepository;
import org.springframework.data.domain.PageRequest;

import java.time.ZonedDateTime;
import java.util.List;

public class PartialLoadRatings {

    private static final int MAX_LOAD_RATING = 4;

    private final RatingRepository ratingRepository;



    public PartialLoadRatings(RatingRepository ratingRepository){

        this.ratingRepository = ratingRepository;
    }



    public List<Rating> load(String lastRating, String userId, String filter){
        Rating rating = new Rating();

        if(lastRating.equals("0"))
            rating.setTimestamp(ZonedDateTime.now());
        else
            rating = ratingRepository.findById(lastRating).get();


        List<Rating> ratings = ratingRepository.getRatingsByTimestampIsLessThanAndUserIdOrderByTimestampDesc(rating.getTimestamp(), userId, PageRequest.of(0, MAX_LOAD_RATING));

        return ratings;
    }

}
