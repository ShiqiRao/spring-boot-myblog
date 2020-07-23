package com.example.myblog.config;

import com.example.myblog.entity.Article;
import com.example.myblog.entity.User;
import com.example.myblog.repository.ArticleRepository;
import com.example.myblog.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlogConfiguration implements ApplicationRunner{

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public BlogConfiguration(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User hanmeimei = userRepository.save(new User().setLogin("meimeihan").setFirstName("meimei").setLastName("han"));
        articleRepository.save(new Article()
                .setTitle("Spring Framework 5.0 goes GA")
                .setHeadline("headline1")
                .setContent("content1")
                .setAuthor(hanmeimei));
        articleRepository.save(new Article()
                .setTitle("the title2")
                .setHeadline("headline2")
                .setContent("content2")
                .setAuthor(hanmeimei));
    }
}
