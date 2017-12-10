package iosr.facebookapp.stats.sqs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostMessage {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final SimpleDateFormat FACEBOOK_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @JsonProperty("id")
    private String id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("link")
    private String link;

    @JsonProperty("shares")
    private int shares;

    @JsonProperty("likes")
    private int likes;

    @JsonProperty("comments")
    private int comments;

    @JsonProperty("createdTime")
    private String createdTime;

    public static PostMessage fromJSON(String messageJSON) throws IOException {
        return OBJECT_MAPPER.readValue(messageJSON, PostMessage.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public Date getCreatedTimeDate() throws ParseException {
        return FACEBOOK_DATE_FORMAT.parse(createdTime);
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return getId() + " " + getLink() + " " + getCreatedTime() + " " + getLikes() + " " + getComments() + " " + getShares() + " " + getMessage();
    }
}
