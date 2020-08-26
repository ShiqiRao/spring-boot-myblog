package com.example.myblog.domain;

import com.example.myblog.validator.Author;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Accessors(chain = true)
@Setter
@Getter
public class SubmitArticleQuery {
    //标题
    @NotNull(message = "Title must not be null.")
    private String title;
    //副标题
    @NotNull(message = "Headline must not be null.")
    private String headline;
    //正文
    @NotNull(message = "Content must not be null.")
    private String content;
    //作者名
    @Author
    @NotNull(message = "Author must not be null.")
    private String author;
}
