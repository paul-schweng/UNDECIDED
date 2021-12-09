package cyou.ted2.undecided.models;

import java.io.Serializable;

public class VotePK implements Serializable {

    private User user;
    private Rating rating;

    public VotePK(User user, Rating rating) {
        this.user = user;
        this.rating = rating;
    }


}
