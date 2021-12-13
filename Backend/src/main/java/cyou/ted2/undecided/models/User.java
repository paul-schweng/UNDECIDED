package cyou.ted2.undecided.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import cyou.ted2.undecided.providers.MyGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Collection;

@Entity
public class User {

    @Id
    @GeneratedValue(generator = MyGenerator.generatorName)
    @GenericGenerator(name = MyGenerator.generatorName, strategy = "cyou.ted2.undecided.providers.MyGenerator")
    @Column(name = "userid", nullable = false)
    private String id;
    private String name, username,
            email, password, usertype,
            description, profileImage, language;
    private LocalDate birthdate;
    private LocalDate registerDate;
    private boolean verified, isDarkTheme;
    private int ratingsNum, followerNum, followingNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isDarkTheme() {
        return isDarkTheme;
    }

    public void setDarkTheme(boolean darkTheme) {
        isDarkTheme = darkTheme;
    }

    public int getRatingsNum() {
        return ratingsNum;
    }

    public void setRatingsNum(int ratingsNum) {
        this.ratingsNum = ratingsNum;
    }

    public int getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(int followerNum) {
        this.followerNum = followerNum;
    }

    public int getFollowingNum() {
        return followingNum;
    }

    public void setFollowingNum(int followingNum) {
        this.followingNum = followingNum;
    }

    public void update(User u) throws IllegalAccessException {
        for (Field field : User.class.getDeclaredFields()) {
            if(field.get(u) != null ){
                field.set(this, field.get(u));
            }
        }
    }
}
