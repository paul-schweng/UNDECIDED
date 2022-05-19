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

import java.util.*;

import static cyou.ted2.undecided.controller.SearchData.RATING;
import static cyou.ted2.undecided.controller.SearchData.USER;


@RestController
@RequestMapping("/api/search")
public class SearchController {

    public static final int MAX_LOAD_RESULTS = 8;

    static {
        int sum = 0;
        for (SearchData data : SearchData.values())
            sum += data.GET_MAX();

        assert sum == 1;
    }


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

        if(searchQuery.getQuery().equals("*"))
            searchQuery.setQuery("");

        if(searchQuery.filters.contains("ratings")){

            //TODO: implement Label filter
            if(searchQuery.filterMap.containsKey("labels")){

            }

            List<Rating> ratings = ratingRepository.getRatingsByQuerySearch(searchQuery.getQuery(), searchQuery.getLoadedRatings(), RATING.GET_MAX());

            ratings.forEach(Rating::clearData);
            ratings.forEach(r ->{
                        r.setLiked(isLiked(r.getId()));
                        r.setModelType("rating");
            });

            results.addAll(ratings);
        }
        if(searchQuery.filters.contains("profile")){
            List<User> users = userRepository.getUsersByQuerySearch(searchQuery.getQuery(), searchQuery.getLoadedUsers(), USER.GET_MAX());

            int idx = 0;
            for (User user : users) {
                user.clearData(false);
                user.setModelType("user");
                results.add( idx = Tools.randomInt(idx+1, results.size()-1) , user);
            }
        }


        return ResponseEntity.ok().body(results);
    }

    public boolean isLiked(String id){
        String userId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return voteRepository.existsById(new VotePK(userId, id));
    }

}

class SearchQuery {
    private final static List<Object> AVAILABLE_FILTERS = List.of("ratings", "profile", "location", "brand");

    String query;
    int loadedUsers, loadedRatings;
    List<Object> filters = AVAILABLE_FILTERS;
    HashMap<String, List<Object>> filterMap = new HashMap<>();


    public int getLoadedRatings() {
        return loadedRatings;
    }

    public void setLoadedRatings(int loadedRatings) {
        this.loadedRatings = loadedRatings;
    }

    public List<Object> getFilters() {
        return filters;
    }

    public void setFilters(List<Object> filters) {
        if(filters.isEmpty())
            return;

        // filters = [ "ratings", labels={[1,2,3]} ]

        List<Object> temp = new ArrayList<>(filters);
        for (Object filter : filters) {
            if(!(filter instanceof Map))
                continue;

            String key = (String) ((Map<String, ?>) filter).keySet().toArray()[0];
            filterMap.put(key, ((Map<?, List<Object>>) filter).get(key));

            temp.remove(filter);
        }

        this.filters = temp.isEmpty() ? AVAILABLE_FILTERS : temp;
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


// must add up to 1
enum SearchData{
    USER(0.25),
    RATING(0.75);

    private final int data;

    SearchData(double x){
        data = (int) Math.round(SearchController.MAX_LOAD_RESULTS * x);
    }

    public int GET_MAX(){
        return data;
    }

}
