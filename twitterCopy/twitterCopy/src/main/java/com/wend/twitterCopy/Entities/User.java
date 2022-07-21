package com.wend.twitterCopy.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank
    @Column(name = "user_name", unique = true)
    private String userName;

    @JsonIgnore
    @NotBlank
    @Column(name = "user_password")
    private String userPassword;

    @Email
    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "followers")
    private Integer followers;


    @Column(name = "following")
    private Integer following;

    @JsonIgnore
    @OneToMany(mappedBy = "sender")
    List<Message> messagesSent = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "receiver")
    List<Message> messagesReceived = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
            @JoinTable(
                    name = "followers",
                    joinColumns = @JoinColumn(name = "followers_id"),
                    inverseJoinColumns = @JoinColumn(name = "following_id"))
    Set<User> listOfFollowers = new HashSet<>();


    @JsonIgnore
    @ManyToMany
            @JoinTable(
                    name = "followers",
                    joinColumns = @JoinColumn(name="following_id"),
                    inverseJoinColumns = @JoinColumn(name = "followers_id"))
    Set<User> listOfFollowing = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Twit> userTwits = new ArrayList<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<Twit> getUserTwits() {
        return userTwits;
    }

    public void addUserTwits(Twit twit) {
        userTwits.add(twit);
    }

    public void setUserTwits(List<Twit> userTwits) {
        this.userTwits = userTwits;
    }


    public Set<User> getListOfFollowers() {
        return listOfFollowers;
    }

    public void setListOfFollowers(Set<User> listOfFollowers) {
        this.listOfFollowers = listOfFollowers;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowing() {
        return following;
    }

    public void setFollowing(Integer following) {
        this.following = following;
    }

    public Set<User> getListOfFollowing() {
        return listOfFollowing;
    }

    public void setListOfFollowing(Set<User> listOfFollowing) {
        this.listOfFollowing = listOfFollowing;
    }





    public void addToListOfFollowers(User user) {
        this.listOfFollowers.add(user);
    }

    public void addToListOfFollowing(User user) {
        this.listOfFollowing.add(user);
    }

    public void removeFromListOfFollowing(User user) {
        this.listOfFollowing.remove(user);
    }

    public void removeFromListOfFollowers(User user) {
        this.listOfFollowers.remove(user);
    }

    public void addToMessagesSent(Message message) {
        messagesSent.add(message);
    }

    public void addToMessagesReceived(Message message) {
        messagesReceived.add(message);
    }
}