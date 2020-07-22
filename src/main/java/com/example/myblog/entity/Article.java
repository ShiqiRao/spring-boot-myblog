package com.example.myblog.entity;

import com.example.myblog.util.CommonUtil;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String headline;
    private String content;
    @ManyToOne
    private User author;
    private String slug;
    private LocalDateTime addedAt;

    public Article() {
        addedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Article setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Article setTitle(String title) {
        this.title = title;
        this.slug = CommonUtil.toSlug(title);
        return this;
    }

    public String getHeadline() {
        return headline;
    }

    public Article setHeadline(String headline) {
        this.headline = headline;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Article setAuthor(User author) {
        this.author = author;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Article setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public Article setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
        return this;
    }
}
