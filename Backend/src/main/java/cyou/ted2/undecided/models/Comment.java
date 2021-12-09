package cyou.ted2.undecided.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    private String id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "ratingID")
    private Rating rating;

    private LocalDateTime timestamp;

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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
