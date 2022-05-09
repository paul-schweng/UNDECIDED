package cyou.ted2.undecided.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import cyou.ted2.undecided.providers.MyGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class Rating extends Model{

    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName, strategy = "cyou.ted2.undecided.providers.MyGenerator")
    @Column(name = "ratingid", nullable = false)
    protected String id;
    protected String description;

    @OneToMany
    protected List<Type> types;

    @Column(name = "labels")
    @JsonIgnore
    protected String labelList;

    @Transient
    @JsonInclude
    protected List<Integer> labels;

    @Transient
    @JsonInclude
    protected boolean isLiked;

    @ManyToMany
    protected List<User> friends;
    protected ZonedDateTime timestamp;
    protected double stars;
    protected int voteNum, commentNum, imageNum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productid")
    protected Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid")
    protected User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "locationid")
    protected Location location;


    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }


    public List<Type> getTypes() {
        return types;
    }



    public List<User> getFriends() {
        return friends;
    }

    public ZonedDateTime getTimestamp() {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public int getImageNum() {
        return imageNum;
    }

    public void setImageNum(int imageNum) {
        this.imageNum = imageNum;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
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


    public String getLabelList() {
        if(labelList == null && labels != null)
            labelList = labels.toString();
        return labelList;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }

    public List<Integer> getLabels() throws JsonProcessingException {
        if(labels == null)
            return new ObjectMapper().readValue(labelList, new TypeReference<List<Integer>>(){});
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
        this.labelList = labels.toString();
    }

    @JsonProperty("isLiked")
    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
