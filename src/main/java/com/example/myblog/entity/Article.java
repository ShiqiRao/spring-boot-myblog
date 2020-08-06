package com.example.myblog.entity;

import com.example.myblog.util.CommonUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Accessors(chain = true)
@Setter
@Getter
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

    public Article setTitle(String title) {
        this.title = title;
        this.slug = CommonUtil.toSlug(title);
        return this;
    }
}
