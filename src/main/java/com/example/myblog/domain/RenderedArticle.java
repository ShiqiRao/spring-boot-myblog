package com.example.myblog.domain;

import com.example.myblog.entity.User;

public class RenderedArticle {
    private String slug;
    private String title;
    private String headline;
    private String content;
    private User author;
    private String addedAt;

    public String getSlug() {
        return slug;
    }

    public RenderedArticle setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RenderedArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getHeadline() {
        return headline;
    }

    public RenderedArticle setHeadline(String headline) {
        this.headline = headline;
        return this;
    }

    public String getContent() {
        return content;
    }

    public RenderedArticle setContent(String content) {
        this.content = content;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public RenderedArticle setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public RenderedArticle setAddedAt(String addedAt) {
        this.addedAt = addedAt;
        return this;
    }
}
