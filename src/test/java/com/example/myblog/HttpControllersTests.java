package com.example.myblog;

import com.example.myblog.controller.ArticleController;
import com.example.myblog.entity.Article;
import com.example.myblog.entity.User;
import com.example.myblog.repository.ArticleRepository;
import com.example.myblog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
public class HttpControllersTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ArticleRepository articleRepository;

    @Test
    void listArticles() throws Exception {
        User leili = new User().setLogin("leili").setFirstName("lei").setLastName("li");
        Article article1 = new Article().setTitle("Spring Framework 5.0 goes GA").setHeadline("headline1").setContent("content1")
                .setAuthor(leili);
        Article article2 = new Article().setTitle("Spring Framework 4.3 goes GA").setHeadline("headline2").setContent("content2")
                .setAuthor(leili);
        when(articleRepository.findAllByOrderByAddedAtDesc()).thenReturn(Arrays.asList(article1, article2));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/article/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].author.login").value(leili.getLogin()))
                .andExpect(jsonPath("$.[0].slug").value(article1.getSlug()))
                .andExpect(jsonPath("$.[1].author.login").value(leili.getLogin()))
                .andExpect(jsonPath("$.[1].slug").value(article2.getSlug()));
    }

}
