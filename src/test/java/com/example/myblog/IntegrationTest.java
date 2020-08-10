package com.example.myblog;

import com.example.myblog.domain.SubmitArticleQuery;
import com.example.myblog.util.CommonUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {BlogApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;


    @BeforeAll
    void setup() {
        System.out.println(">> Setup");
    }

    @Test
    void assertBlogPageTitle_Content_And_StatusCode() {
        System.out.println(">> Assert blog page title, content and status code");
        ResponseEntity<String> entity = restTemplate.getForEntity("/", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("<title>Blog</title>", "title1");
    }

    @Test
    void assertArticlePageTitle_Content_And_StatusCode() {
        System.out.println(">> Assert article page title, content and status code");
        String title = "title1";
        ResponseEntity<String> entity = restTemplate.getForEntity(String.format("/article/%s", CommonUtil.toSlug(title)), String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains(title, "headline1", "content1");
    }

    @Test
    void submitAnArticle() {
        System.out.println(">> Submit an article");
        SubmitArticleQuery queryFromAnonymous = new SubmitArticleQuery()
                .setAuthor("anonymous")
                .setTitle("title")
                .setHeadline("headline")
                .setContent("content");
        ResponseEntity<String> entity = restTemplate.postForEntity("/article", queryFromAnonymous, String.class);
        //若查找不到"anonymous"这位作者，将返回错误码400
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        SubmitArticleQuery queryFromLeili = new SubmitArticleQuery()
                .setAuthor("leili")
                .setTitle("title2")
                .setHeadline("headline2")
                .setContent("content2");
        ResponseEntity<String> entity2 = restTemplate.postForEntity("/article", queryFromLeili, String.class);
        //正常的返回结果
        assertThat(entity2.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity2.getBody()).contains("success");
    }

    @AfterAll
    void teardown() {
        System.out.println(">>  Tear down");
    }

}
