package cyou.ted2.undecided.models;

import java.time.LocalDateTime;

public class Comment {
    private String id, content;
    private User user;
    private Rating rating;
    private LocalDateTime timestamp;
}
