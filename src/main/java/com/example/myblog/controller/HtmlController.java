package com.example.myblog.controller;

import com.example.myblog.config.BlogProperties;
import com.example.myblog.domain.SubmitArticleQuery;
import com.example.myblog.domain.RenderedArticle;
import com.example.myblog.entity.Article;
import com.example.myblog.entity.User;
import com.example.myblog.repository.ArticleRepository;
import com.example.myblog.repository.UserRepository;
import com.example.myblog.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Controller
public class HtmlController {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final BlogProperties blogProperties;

    @GetMapping("/")
    public String blog(Model model) {
        model.addAttribute("title", blogProperties.getTitle());
        model.addAttribute("banner", blogProperties.getBanner());
        model.addAttribute("articles", StreamSupport.stream(articleRepository.findAllByOrderByAddedAtDesc().spliterator(), true)
                .map(this::render)
                .collect(Collectors.toList()));
        return "blog";
    }

    @GetMapping("/article/{slug}")
    public String article(@PathVariable String slug, Model model) {
        Article article = articleRepository.findBySlug(slug);
        if (article == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist");
        }
        RenderedArticle renderedArticle = render(article);
        model.addAttribute("title", renderedArticle.getTitle());
        model.addAttribute("article", renderedArticle);
        return "article";
    }

    @GetMapping("/writing")
    public String article(Model model) {
        //用于填写页面的展示
        Iterable<User> userList = userRepository.findAll();
        model.addAttribute("title", "writing");
        model.addAttribute("users", userList);
        return "writing";
    }

    @PostMapping("/article")
    @ResponseBody
    public String submitArticle(@RequestBody SubmitArticleQuery query) {
        //用于接收POST请求
        User author = userRepository.findByLogin(query.getAuthor());
        if (author == null) {
            //返回400错误码
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This user does not exist");
        }
        Article toSave = new Article().setAuthor(author)
                .setTitle(query.getTitle())
                .setHeadline(query.getHeadline())
                .setContent(query.getContent())
                .setSlug(CommonUtil.toSlug(query.getTitle()));
        articleRepository.save(toSave);
        return "success";
    }

    private RenderedArticle render(Article article) {
        return new RenderedArticle()
                .setTitle(article.getTitle())
                .setHeadline(article.getHeadline())
                .setSlug(article.getSlug())
                .setContent(article.getContent())
                .setAuthor(article.getAuthor())
                .setAddedAt(CommonUtil.format(article.getAddedAt()));
    }

}
