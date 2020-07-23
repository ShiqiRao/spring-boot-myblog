package com.example.myblog.config;

import com.example.myblog.domain.Banner;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("blog")
public class BlogProperties {

    private String title;
    private Banner banner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }
}
