package com.example.myblog;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenUploadFile_thenReturnAnUrl() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders
                        .multipart("/file")
                        .file(
                                new MockMultipartFile("file",
                                        "test.txt",
                                        ",multipart/form-data",
                                        "hello upload".getBytes(StandardCharsets.UTF_8))
                        )
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        //断言返回结果中包含路径名”file“
        assertThat(result).contains("file");
    }

    @Test
    public void uploadFile_AndDownloadIt() throws Exception {
        String content = "hello upload";
        String downloadUrl = mockMvc.perform(
                MockMvcRequestBuilders
                        .multipart("/file")
                        .file(
                                new MockMultipartFile("file",
                                        "test.txt",
                                        ",multipart/form-data",
                                        content.getBytes(StandardCharsets.UTF_8))
                        )
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        MvcResult downloadResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get(downloadUrl)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        ).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assertThat(downloadResult.getResponse().getContentAsString()).isEqualTo(content);
    }

}
