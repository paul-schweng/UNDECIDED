package cyou.ted2.undecided.controller;

import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.repository.RatingRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class RatingController {

    private final RatingRepository repository;

    RatingController(RatingRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/rating")
    @ResponseBody
    Iterable<Rating> getAllRatings() {
        repository.findAll().forEach(e -> System.out.println(e.toString()));
        return repository.findAll();
    }

    @PostMapping("/rating")
    @ResponseBody
        //json
    Rating newRating(@RequestBody Rating newRating) {
        return repository.save(newRating);
    }

    @PutMapping("/rating")
    @ResponseBody
    Rating updateRating(@RequestBody Rating updatedRating) {
        return repository.save(updatedRating);
    }
}