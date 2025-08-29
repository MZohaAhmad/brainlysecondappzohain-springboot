package com.secondbrain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import java.time.LocalDateTime;

@Document(collection = "contents")
public class Content {
    @Id
    private String id;
    
    private String link;
    private String contentType; // Youtube, Twitter, Notion
    private String title;
    private String tag;
    
    @Indexed
    private String userId;
    
    private LocalDateTime createdAt;

    // Constructors
    public Content() {
        this.createdAt = LocalDateTime.now();
    }

    public Content(String link, String contentType, String title, String tag, String userId) {
        this.link = link;
        this.contentType = contentType;
        this.title = title;
        this.tag = tag;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}


