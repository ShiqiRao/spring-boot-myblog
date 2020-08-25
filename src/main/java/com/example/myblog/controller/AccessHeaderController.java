package com.example.myblog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/api/header")
public class AccessHeaderController {

    @GetMapping("/greeting")
    public String greeting(@RequestHeader("accept-language") String language) {
        //根据Header中的不同属性返回不同问候语
        switch (language) {
            case "zh":
                return "你好";
            case "jp":
                return "こんにちは";
            case "en":
            default:
                return "Hello";
        }
    }

    @GetMapping("/map")
    public String headerMap(@RequestHeader Map<String, String> headers) {
        //返回所有头属性拼接而成的字符串
        return headers
                .entrySet()
                .stream()
                .map(entry -> String.format("key=%s,value=%s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("\r\n"));
    }

    @GetMapping("/multi-value-map")
    public String headerMap(@RequestHeader MultiValueMap<String, String> headers) {
        //返回所有头属性拼接而成的字符串,使用“|”分隔有多个值的属性
        return headers
                .entrySet()
                .stream()
                .map(entry -> String.format("key=%s,value=%s", entry.getKey(), String.join("|", entry.getValue())))
                .collect(Collectors.joining("\r\n"));
    }

    @GetMapping("/http-headers")
    public String httpHeaders(@RequestHeader HttpHeaders httpHeaders) {
        //获取”Accept-Encoding“属性，以逗号分隔多个值
        return String.join(",", Optional.ofNullable(httpHeaders.get("Accept-Encoding"))
                .orElse(new ArrayList<>()));
    }

}
