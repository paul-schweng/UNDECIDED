package cyou.ted2.undecided.models;

import java.io.Serializable;

public class FollowingPK implements Serializable {

    private User user;
    private User following;

    public FollowingPK(User user, User following) {
        this.user = user;
        this.following = following;
    }
}
