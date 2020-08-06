package com.example.myblog.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Setter
@Getter
public class SubmitArticleQuery {
    //标题
    private String title;
    //副标题
    private String headline;
    //正文
    private String content;
    //作者名
    private String author;
}
