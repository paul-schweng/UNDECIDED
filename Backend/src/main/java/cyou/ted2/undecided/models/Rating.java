package cyou.ted2.undecided.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Rating {

    @Id
    @GeneratedValue
    @Column(name = "ratingid")
    private Long id;
    private String description;

    @OneToMany
    private List<Type> types;

    @ElementCollection
    private List<String> images;


    private String labelList;

    @JsonInclude
    @Transient
    private List<Integer> labels;

    @OneToMany(cascade = CascadeType.ALL)
    private List<User> friends;
    private LocalDateTime timestamp;
    private double stars;
    private int voteNum, commentNum;

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "locationid")
    private Location location;

    public Rating(){}
    public Rating(String description, List<String> images, List<Type> types, List<Integer> labels, List<User> friends, LocalDateTime timestamp, double stars, int voteNum, int commentNum, Product product, User user, Location location) {
        this.description = description;
        this.images = images;
        this.types = types;
        this.labels = labels;
        this.friends = friends;
        this.timestamp = timestamp;
        this.stars = stars;
        this.voteNum = voteNum;
        this.commentNum = commentNum;
        this.product = product;
        this.user = user;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public List<Type> getTypes() {
        return types;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public List<User> getFriends() {
        return friends;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getStars() {
        return stars;
    }

    public int getVoteNum() {
        return voteNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public void setVoteNum(int voteNum) {
        this.voteNum = voteNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", image='" + images + '\'' +
                ", types=" + types +
                ", labels=" + labels +
                ", friends=" + friends +
                ", timestamp=" + timestamp +
                ", stars=" + stars +
                ", voteNum=" + voteNum +
                ", commentNum=" + commentNum +
                ", product=" + product +
                ", user=" + user +
                ", location=" + location +
                '}';
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getLabelList() {
        return labelList;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }
}
