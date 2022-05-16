package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.*;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.repository.VoteRepository;
import cyou.ted2.undecided.services.PartialLoadRatings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RatingController {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final PartialLoadRatings partialLoad;
    private final VoteRepository voteRepository;

    RatingController(RatingRepository rRepository, UserRepository uRepository, VoteRepository voteRepository) {
        this.ratingRepository = rRepository;
        this.userRepository = uRepository;
        this.voteRepository = voteRepository;

        partialLoad = new PartialLoadRatings(rRepository, this);
    }

    @GetMapping("/rating/home")
    ResponseEntity<?> getRatingsOfFollowers(@RequestParam("id") String id){
        String principalId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        List<Rating> ratings = partialLoad.load(id, null, "home", 0, principalId);

        ratings.forEach(Rating::clearData);

        return ResponseEntity.ok().body(ratings);
    }


    @GetMapping("/ratings")
    @ResponseBody
    List<Rating> getAllRatingsByUser(@RequestParam("filter") String filter, @RequestParam("id") String id, @RequestParam("i") int rowNum, @RequestParam("userID") String userId) {
        // return ratingRepository.findAllByUserId(userId);

        String principalId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        List<Rating> ratings = partialLoad.load(id, userId, filter, rowNum, principalId);

        ratings.forEach(Rating::clearData);

        return ratings;
    }

    @GetMapping("/rating")
    @ResponseBody
    ResponseEntity<?> getRating(@RequestParam("id") String id) {
        Rating rating = ratingRepository.findById(id).orElse(null);
        if(rating == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        rating.setLiked(isLiked(rating.getId()));
        rating.clearData();
        return ResponseEntity.ok().body(rating);
    }

    @PostMapping("/rating")
    @ResponseBody
        //json
    Rating newRating(@RequestBody Rating newRating) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findUserById(userId);
        newRating.setUser(user);
        newRating.setTimestamp(ZonedDateTime.now());

        newRating.setVoteNum(0);
        newRating.setCommentNum(0);
        newRating.setImageNum(0);

        user.setRatingsNum(user.getRatingsNum() + 1);
        userRepository.save(user);

        ratingRepository.save(newRating);

        newRating.clearData();
        return newRating;
    }

    @PutMapping("/rating")
    @ResponseBody
    ResponseEntity<?> updateRating(@RequestBody Rating updatedRating) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Rating rating = ratingRepository.findById(updatedRating.getId()).orElse(null);

        if(rating == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(!userId.equals(rating.getUser().getId()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        updatedRating.setVoteNum(null);
        updatedRating.setCommentNum(null);
        updatedRating.setImageNum(null);
        updatedRating.setUser(null);

        System.out.println("\n \n");
        System.out.println(rating.getUser().getPassword());


        rating.update(updatedRating);

        System.out.println(rating.getUser().getPassword());
        System.out.println("\n \n");


        rating.setLiked(isLiked(rating.getId()));
        ratingRepository.save(rating);

        rating.clearData();
        return ResponseEntity.accepted().body(rating);
    }

    @DeleteMapping("/rating")
    public ResponseEntity<?> deleteRating(@RequestParam String id) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Rating rating = ratingRepository.findById(id).orElse(null);

        if(rating == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(!userId.equals(rating.getUser().getId()))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        rating.setUser(null);

        ratingRepository.delete(rating);

        User user = userRepository.findUserById(userId);
        user.setRatingsNum(user.getRatingsNum() - 1);
        userRepository.save(user);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    public boolean isLiked(String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return isLiked(id, userId);
    }

    public boolean isLiked(String id, String userId){
        return voteRepository.existsById(new VotePK(userId, id));
    }


    @GetMapping("/rating/liked/{id}")
    public ResponseEntity<?> isRatingLiked(@PathVariable String id){
        Map<String, Boolean> map = new HashMap<>();
        map.put("isLiked", isLiked(id));
        return ResponseEntity.accepted().body(map);
    }

    @GetMapping("/rating/like/{id}")
    public void likeRating(@PathVariable String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean voteExists = voteRepository.existsById(new VotePK(userId, id));
        Rating rating = ratingRepository.findById(id).orElse(null);

        if(!voteExists && rating != null){
            Vote vote = new Vote();
            vote.setTimestamp(ZonedDateTime.now());

            User user = new User();
            user.setId(userId);
            vote.setUser(user);

            vote.setRating(rating);

            voteRepository.save(vote);

            rating.setVoteNum(rating.getVoteNum() + 1);
            ratingRepository.save(rating);
        }
    }

    @GetMapping("/rating/dislike/{id}")
    public void removeLikeRating(@PathVariable String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        boolean voteExists = voteRepository.existsById(new VotePK(userId, id));
        Rating rating = ratingRepository.findById(id).orElse(null);

        if(voteExists && rating != null){
            voteRepository.deleteById(new VotePK(userId, id));

            rating.setVoteNum(rating.getVoteNum() - 1);
            ratingRepository.save(rating);
        }
    }


}
