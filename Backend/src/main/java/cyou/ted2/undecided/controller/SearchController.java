package cyou.ted2.undecided.controller;


import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    public SearchController(RatingRepository ratingRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{query}")
    ResponseEntity<?> getSearchResults(@PathVariable String query) {

        return ResponseEntity.ok().body(Collections.emptyList());
    }

}
