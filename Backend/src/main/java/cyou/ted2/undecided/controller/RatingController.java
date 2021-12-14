package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
class RatingController {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    RatingController(RatingRepository rRepository, UserRepository uRepository) {
        this.ratingRepository = rRepository;
        this.userRepository = uRepository;
    }

    @GetMapping("/ratings")
    @ResponseBody
    Iterable<Rating> getAllRatingsByUser(@RequestParam("filter") String filter) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return ratingRepository.findAllByUserId(userId);
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
        return ratingRepository.save(newRating);
    }

    @PutMapping("/rating")
    @ResponseBody
    Rating updateRating(@RequestBody Rating updatedRating) {
        Rating rating = ratingRepository.findById(updatedRating.getId()).get();
        rating.update(updatedRating);

        return ratingRepository.save(updatedRating);
    }
}