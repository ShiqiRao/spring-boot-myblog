package com.example.myblog;

import com.example.myblog.domain.SubmitArticleQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapper;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenSubmitWrongArgument_thenReturn4xx() throws Exception {
        SubmitArticleQuery submitArticleQuery = new SubmitArticleQuery()
                .setTitle("title")
                .setHeadline("headline")
                //设置content为Null用于测试接口验证结果
                .setContent(null)
                .setAuthor("meimeihan");
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(submitArticleQuery))
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void whenSubmitWrongAuthor_thenReturn4xx() throws Exception {
        SubmitArticleQuery submitArticleQuery = new SubmitArticleQuery()
                .setTitle("title")
                .setHeadline("headline")
                //设置content为Null用于测试接口验证结果
                .setContent("content")
                .setAuthor("anonymous");
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/article")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(submitArticleQuery))
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andDo(print());
    }

}
