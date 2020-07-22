package com.example.myblog.repository;

import com.example.myblog.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

    Article findBySlug(String slug);

    Iterable<Article> findAllByOrderByAddedAtDesc();
}
