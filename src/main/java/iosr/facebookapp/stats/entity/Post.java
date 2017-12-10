package iosr.facebookapp.stats.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post {

    @Id
    private String postId;

    private Integer likes;

    private Integer comments;

    private Integer shares;

    private String link;

    private Date createdTime;

    public Post() {}

    public Post(String postId, Integer likes, Integer comments, Integer shares, String link, Date createdTime) {
        this.postId = postId;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.link = link;
        this.createdTime = createdTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
