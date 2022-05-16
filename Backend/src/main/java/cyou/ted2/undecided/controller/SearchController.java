package cyou.ted2.undecided.controller;


import cyou.ted2.undecided.models.Model;
import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.VotePK;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.repository.VoteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private static final int MAX_LOAD_RATINGS = 5;

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    public SearchController(RatingRepository ratingRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @PostMapping
    ResponseEntity<?> getSearchResults(@RequestBody SearchQuery searchQuery) {
        List<Model> results = new ArrayList<>();

        if(!searchQuery.filters.contains("rating")){
            List<Rating> ratings = ratingRepository.getRatingsByQuerySearch(searchQuery.query, searchQuery.loadedRatings, MAX_LOAD_RATINGS);

            ratings.forEach(Rating::clearData);
            ratings.forEach(r ->{
                        r.setLiked(isLiked(r.getId()));
                        r.setModelType("rating");
            });

            results.addAll(ratings);
        }

        return ResponseEntity.ok().body(results);
    }

    public boolean isLiked(String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return voteRepository.existsById(new VotePK(userId, id));
    }

}

class SearchQuery {
    String query;
    int loadedUsers, loadedRatings;
    List<String> filters = new ArrayList<>();

    public int getLoadedRatings() {
        return loadedRatings;
    }

    public void setLoadedRatings(int loadedRatings) {
        this.loadedRatings = loadedRatings;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getLoadedUsers() {
        return loadedUsers;
    }

    public void setLoadedUsers(int loadedUsers) {
        this.loadedUsers = loadedUsers;
    }

}
