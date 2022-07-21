package com.wend.twitterCopy.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "twits")
public class Twit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "twit_id")
    private Integer twitId;

    @NotBlank
    @Column(name = "twit_message")
    private String twitMessage;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    public Integer getTwitId() {
        return twitId;
    }

    public void setTwitId(Integer twitId) {
        this.twitId = twitId;
    }

    public String getTwitMessage() {
        return twitMessage;
    }

    public void setTwitMessage(String twitMessage) {
        this.twitMessage = twitMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}