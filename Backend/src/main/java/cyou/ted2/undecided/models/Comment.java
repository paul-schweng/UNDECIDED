package cyou.ted2.undecided.models;

import cyou.ted2.undecided.providers.MyGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName, strategy = "cyou.ted2.undecided.providers.MyGenerator")
    @Column(name = "commentid", nullable = false)
    private String id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ratingID")
    private Rating rating;

    private ZonedDateTime timestamp;

    public Rating getRating() {
        return rating;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
