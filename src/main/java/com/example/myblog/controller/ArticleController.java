package com.example.myblog.controller;

import com.example.myblog.entity.Article;
import com.example.myblog.repository.ArticleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public Iterable<Article> findAll() {
        return articleRepository.findAllByOrderByAddedAtDesc();
    }

    @GetMapping("/{slug}")
    public Article findOne(@PathVariable String slug) throws HttpStatusCodeException {
        Article result = articleRepository.findBySlug(slug);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article dose not exit");
        }
        return result;
    }

}
