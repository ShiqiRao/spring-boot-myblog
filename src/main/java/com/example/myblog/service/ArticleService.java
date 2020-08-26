package com.example.myblog.service;

import com.example.myblog.domain.SubmitArticleQuery;
import com.example.myblog.entity.Article;
import com.example.myblog.entity.User;
import com.example.myblog.repository.ArticleRepository;
import com.example.myblog.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article saveArticle(@Validated SubmitArticleQuery query, User author) {
        Article toSave = new Article().setAuthor(author)
                .setTitle(query.getTitle())
                .setHeadline(query.getHeadline())
                .setContent(query.getContent())
                .setSlug(CommonUtil.toSlug(query.getTitle()));
        articleRepository.save(toSave);
        return toSave;
    }

}
