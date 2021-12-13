package cyou.ted2.undecided.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@IdClass(FollowingPK.class)
public class Following {
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "userID", nullable = false)
    private User user;
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "followingID", nullable = false)
    private User following;
    private LocalDateTime followDate;

    public User getFollowing() {
        return following;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getFollowDate() {
        return followDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public void setFollowDate(LocalDateTime followDate) {
        this.followDate = followDate;
    }
}
