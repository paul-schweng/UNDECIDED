package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.RatingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
class RatingController {

    private final RatingRepository repository;

    RatingController(RatingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/ratings")
    @ResponseBody
    Iterable<Rating> getAllRatings(@RequestParam("filter") String filter) {
        repository.findAll().forEach(e -> System.out.println(e.toString()));
        return repository.findAll();
    }

    @GetMapping("/rating")
    @ResponseBody
    Optional<Rating> getRating(@RequestParam("id") String id) {
        return repository.findById(Long.parseLong(id));
    }

    @PostMapping("/rating")
    @ResponseBody
        //json
    Rating newRating(@RequestBody Rating newRating) {
       /* Long userId = Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        User user = new User();
        user.setId(userId);
        newRating.setUser(user);

        */
        return repository.save(newRating);
    }

    @PutMapping("/rating")
    @ResponseBody
    Rating updateRating(@RequestBody Rating updatedRating) {
        return repository.save(updatedRating);
    }
}