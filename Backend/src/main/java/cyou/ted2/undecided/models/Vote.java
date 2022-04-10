package cyou.ted2.undecided.models;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@IdClass(VotePK.class)
public class Vote {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "ratingID")
    private Rating rating;
    private ZonedDateTime timestamp;

    public User getUser() {
        return user;
    }

    public Rating getRating() {
        return rating;
    }
}
