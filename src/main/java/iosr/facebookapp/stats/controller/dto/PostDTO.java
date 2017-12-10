package iosr.facebookapp.stats.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PostDTO {
    private Integer id;

    private String postId;

    private Integer likes;

    private Integer comments;

    private Integer shares;

    private String link;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date createdTime;

    public PostDTO() {}

    public PostDTO(Integer id, String postId, Integer likes, Integer comments, Integer shares, String link, Date createdTime) {
        this.id = id;
        this.postId = postId;
        this.likes = likes;
        this.comments = comments;
        this.shares = shares;
        this.link = link;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }
}
