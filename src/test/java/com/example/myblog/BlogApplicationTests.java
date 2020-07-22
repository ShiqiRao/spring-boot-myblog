package com.example.myblog;

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
class BlogApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;


    @BeforeAll
    void setup() {
        System.out.println(">> Setup");
    }

    @Test
    void assertBlogPageTitle_Content_And_StatusCode() {
        ResponseEntity<String> entity = restTemplate.getForEntity("/", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).contains("<h1>Blog</h1>");
    }

    @Test
    void assertArticlePageTitle_Content_And_StatusCode() {
        System.out.println(">> TODO");
    }

    @AfterAll
    void teardown() {
        System.out.println(">>  Tear down");
    }

}
