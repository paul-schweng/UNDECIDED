package cyou.ted2.undecided.models;

import java.io.Serializable;

public class FollowingPK implements Serializable {

    private String user;
    private String following;

    public FollowingPK(String user, String following) {
        this.user = user;
        this.following = following;
    }

    public FollowingPK(){}
}
