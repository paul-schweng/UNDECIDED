package cyou.ted2.undecided.models;

import java.time.LocalDateTime;
import java.util.List;

public class Rating {

    private String id, description, image;
    private List<String> types;
    private List<Integer> labels;
    private List<User> friends;
    private LocalDateTime timestamp;
    private double stars;
    private int voteNum, commentNum;
    private Product product;
    private User user;
    private Location location;

}