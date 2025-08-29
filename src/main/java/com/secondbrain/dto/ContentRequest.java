package com.secondbrain.dto;

import jakarta.validation.constraints.NotBlank;

public class ContentRequest {
    @NotBlank(message = "Link is required")
    private String link;
    
    @NotBlank(message = "Content type is required")
    private String contentType;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Tag is required")
    private String tag;

    // Constructors
    public ContentRequest() {}

    public ContentRequest(String link, String contentType, String title, String tag) {
        this.link = link;
        this.contentType = contentType;
        this.title = title;
        this.tag = tag;
    }

    // Getters and Setters
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
}


