package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.services.PartialLoadRatings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class RatingController {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final PartialLoadRatings partialLoad;

    RatingController(RatingRepository rRepository, UserRepository uRepository) {
        this.ratingRepository = rRepository;
        this.userRepository = uRepository;
        partialLoad = new PartialLoadRatings(rRepository);
    }

    @GetMapping("/ratings")
    @ResponseBody
    Iterable<Rating> getAllRatingsByUser(@RequestParam("filter") String filter, @RequestParam("id") String id, @RequestParam("i") int rowNum, @RequestParam("userID") String userId) {
        // return ratingRepository.findAllByUserId(userId);

        return partialLoad.load(id, userId, filter, rowNum);
    }

    @GetMapping("/rating")
    @ResponseBody
    Optional<Rating> getRating(@RequestParam("id") String id) {
        return ratingRepository.findById(id);
    }

    @PostMapping("/rating")
    @ResponseBody
        //json
    Rating newRating(@RequestBody Rating newRating) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findUserById(userId);
        newRating.setUser(user);
        newRating.setTimestamp(ZonedDateTime.now());

        user.setRatingsNum(user.getRatingsNum() + 1);
        userRepository.save(user);

        return ratingRepository.save(newRating);
    }

    @PutMapping("/rating")
    @ResponseBody
    ResponseEntity<?> updateRating(@RequestBody Rating updatedRating) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Rating rating = ratingRepository.findById(updatedRating.getId()).get();

        if(!userId.equals(rating.getUser().getId()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        rating.update(updatedRating);

        return ResponseEntity.accepted().body(ratingRepository.save(updatedRating));
    }

    @DeleteMapping("/rating")
    public ResponseEntity<?> deleteRating(@RequestParam String id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Rating rating = ratingRepository.findById(id).get();

        if(!userId.equals(rating.getUser().getId()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        rating.setUser(null);

        ratingRepository.delete(rating);

        User user = userRepository.findUserById(userId);
        user.setRatingsNum(user.getRatingsNum() - 1);
        userRepository.save(user);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
