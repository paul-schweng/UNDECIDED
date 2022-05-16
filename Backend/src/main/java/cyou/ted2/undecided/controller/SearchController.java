package cyou.ted2.undecided.controller;


import cyou.ted2.undecided.models.Model;
import cyou.ted2.undecided.models.Rating;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.models.VotePK;
import cyou.ted2.undecided.repository.RatingRepository;
import cyou.ted2.undecided.repository.UserRepository;
import cyou.ted2.undecided.repository.VoteRepository;
import cyou.ted2.undecided.tools.Tools;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private static final int MAX_LOAD_RATINGS = 5;
    private static final int MAX_LOAD_USERS = 2;

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

        if(searchQuery.filters.contains("rating")){
            List<Rating> ratings = ratingRepository.getRatingsByQuerySearch(searchQuery.getQuery(), searchQuery.getLoadedRatings(), MAX_LOAD_RATINGS);

            ratings.forEach(Rating::clearData);
            ratings.forEach(r ->{
                        r.setLiked(isLiked(r.getId()));
                        r.setModelType("rating");
            });

            results.addAll(ratings);
        }
        if(searchQuery.filters.contains("user")){
            List<User> users = userRepository.getUsersByQuerySearch(searchQuery.getQuery(), searchQuery.getLoadedUsers(), MAX_LOAD_USERS);

            users.forEach(u -> {
                u.clearData(false);
                u.setModelType("user");

                int idx = Tools.randomInt(0, results.size());
                results.add(idx, u);
            });

        }


        return ResponseEntity.ok().body(results);
    }

    public boolean isLiked(String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return voteRepository.existsById(new VotePK(userId, id));
    }

}

class SearchQuery {
    private final static List<String> AVAILABLE_FILTERS = List.of("rating", "user");

    String query;
    int loadedUsers, loadedRatings;
    List<String> filters = AVAILABLE_FILTERS;


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
        // check if filters are accepted
        for (String filter : filters) {
            if(!AVAILABLE_FILTERS.contains(filter))
                return;
        }
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
