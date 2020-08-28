package com.example.myblog.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@ApiModel(description = "用于统一RESTful服务返回内容")
@Accessors(chain = true)
@Setter
@Getter
public class Result<T> {
    @ApiModelProperty("业务状态码")
    private int code;
    @ApiModelProperty("响应内容")
    private T data;
    @ApiModelProperty("错误信息")
    private String message;

    private final static int SUCCESS = 0;
    private final static int FAIL = -1;

    public static <T> Result<T> ok(T data) {
        return new Result<T>()
                .setCode(SUCCESS)
                .setData(data);
    }

    public static <T> Result<T> failed(String message) {
        return new Result<T>()
                .setCode(FAIL)
                .setMessage(message);
    }
}
