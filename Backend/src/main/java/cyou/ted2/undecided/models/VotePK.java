package cyou.ted2.undecided.models;

import java.io.Serializable;

public class VotePK implements Serializable {

    private String user;
    private String rating;

    public VotePK(String user, String rating) {
        this.user = user;
        this.rating = rating;
    }

    public VotePK(){}

}
