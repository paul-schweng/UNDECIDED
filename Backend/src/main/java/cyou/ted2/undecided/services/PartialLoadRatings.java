package cyou.ted2.undecided.services;

import com.google.gson.Gson;
import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.repository.RatingRepository;
import org.springframework.data.domain.PageRequest;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class PartialLoadRatings {

    private static final int MAX_LOAD_RATING = 4;

    private final RatingRepository ratingRepository;



    public PartialLoadRatings(RatingRepository ratingRepository){

        this.ratingRepository = ratingRepository;
    }



    public List<Rating> load(String lastRating, String userId, String filter, int rowNum){
        Rating rating = new Rating();

        List<Rating> ratings = Collections.emptyList();

        switch (filter){
            case "latest":
                if(lastRating.equals("0"))
                    rating.setTimestamp(ZonedDateTime.now());
                else
                    rating = ratingRepository.findById(lastRating).get();
                ratings = ratingRepository.getRatingsByTimestampIsLessThanAndUserIdOrderByTimestampDesc(rating.getTimestamp(), userId, PageRequest.of(0, MAX_LOAD_RATING));
                break;
            case "highest":

                ratings = ratingRepository.getBestRatings(userId, rowNum, MAX_LOAD_RATING);
                break;
            case "worst":
                ratings = ratingRepository.getWorstRatings(userId, rowNum, MAX_LOAD_RATING);
                break;
            case "likes":

                break;
            case "comments":

                break;
        }

        System.out.println(new Gson().toJson(ratings));

        return ratings;
    }

}
