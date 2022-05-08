package cyou.ted2.undecided.services;

import com.google.gson.Gson;
import cyou.ted2.undecided.controller.RatingController;
import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.repository.RatingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

public class PartialLoadRatings {

    private static final int MAX_LOAD_RATING = 4;

    private final RatingRepository ratingRepository;
    private final RatingController ratingController;



    public PartialLoadRatings(RatingRepository ratingRepository, RatingController ratingController){
        this.ratingController = ratingController;
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
                    rating = ratingRepository.findById(lastRating).orElse(null);

                if(rating == null)
                    break;

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

        ratings.forEach(r ->
                r.setLiked(ratingController.isLiked(r.getId(), userId))
        );

        return ratings;
    }

}
