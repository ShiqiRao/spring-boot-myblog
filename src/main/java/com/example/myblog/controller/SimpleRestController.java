package com.example.myblog.controller;

import com.example.myblog.domain.ExampleQuery;
import com.example.myblog.domain.Result;
import io.swagger.annotations.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


@Api(tags = "RESTful服务传参示例")
@RestController
@RequestMapping("/api/simple")
public class SimpleRestController {

    @ApiOperation(value = "无参GET请求", notes = "用于演示通过无参GET请求的形式对接口进行请求")
    @GetMapping("/no-param")
    public Result<String> noParam() {
        //无参
        return Result.ok("No parameter.");
    }

    @ApiOperation(value = "单个参数的GET请求", notes = "用于演示通过单参数GET请求的形式对接口进行请求")
    @ApiImplicitParam(name = "implicit", value = "提供隐含参数的输入方式")
    @GetMapping(path = "/single-param", params = "implicit")
    public Result<String> singleParam(@ApiParam(name = "单参数") String param) {
        //单个可选参数
        return Result.ok("The parameter is :" + param);
    }

    @GetMapping("/single-param-with-annotation")
    public Result<String> singleParamWithAnnotation(@RequestParam("parameter") String param) {
        //单个必传参数
        return Result.ok("The parameter is :" + param);
    }

    @GetMapping("/single-param-with-default-value")
    public Result<String> singleParamWithDefaultValue(@RequestParam(defaultValue = "default") String param) {
        //单个必传参数
        return Result.ok("The parameter is :" + param);
    }

    @PostMapping("/few-params-with-annotation")
    public Result<String> fewParamsWithAnnotation(@RequestParam String paramA, @RequestParam(required = false) Integer paramB) {
        //paramA必传，paramB可选
        return Result.ok(String.format("paramA is :%s,paramB is :%s", paramA, paramB));
    }

    @PostMapping("/large-number-params")
    public Result<String> largeNumberParams(@RequestParam String paramA, @RequestParam String paramB, @RequestParam String paramC, @RequestParam String paramD,
                                            @RequestParam String paramE, @RequestParam String paramF, @RequestParam String paramG, @RequestParam String paramH,
                                            @RequestParam String paramI) {
        return Result.ok(String.format("paramA is :%s,paramB is :%s,paramC is :%s,paramD is :%s,paramE is :%s,paramF is :%s," +
                "paramG is :%s,paramH is :%s,paramI is :%s,", paramA, paramB, paramC, paramD, paramE, paramF, paramG, paramH, paramI));
    }

    @PostMapping("/large-number-params-in-body")
    public Result<String> largeNumberParams(@RequestBody ExampleQuery query) {
        return Result.ok(String.format("paramA is :%s,paramB is :%s,paramC is :%s,paramD is :%s,paramE is :%s,paramF is :%s," +
                        "paramG is :%s,paramH is :%s,paramI is :%s,", query.getParamA(), query.getParamB(), query.getParamC(),
                query.getParamD(), query.getParamE(), query.getParamF(), query.getParamG(), query.getParamH(), query.getParamI()));
    }

    @GetMapping("/greeting")
    public ResponseEntity<Result<String>> greeting() {
        return new ResponseEntity<>(Result.ok("Hello there."), HttpStatus.OK);
    }

    @GetMapping("/custom-header")
    public ResponseEntity<Result<String>> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "customHeader");
        return new ResponseEntity<>(Result.ok("Hello there."), headers, HttpStatus.OK);
    }

    @ApiOperation(value = "下一个生日", notes = "输入出生年月日，计算到下一个生日的天数")
    @ApiResponses({
            @ApiResponse(code = 400, message = "输入日期大于当前日期"),
            @ApiResponse(code = 200, message = "成功")
    })
    @GetMapping("/next-birth-day/{year}/{month}/{day}")
    public ResponseEntity<Result<Long>> nextBirthday(@PathVariable int year, @PathVariable int month, @PathVariable int day) {
        LocalDate birthDate = LocalDate.of(year, month, day);
        if (birthDate.isAfter(LocalDate.now())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        LocalDate nextBirthDay = LocalDate.of(LocalDate.now().getYear(), birthDate.getMonth(), birthDate.getDayOfMonth());
        if (nextBirthDay.isBefore(LocalDate.now())) {
            nextBirthDay = nextBirthDay.plusYears(1);
        }
        return new ResponseEntity<>(Result.ok(DAYS.between(LocalDate.now(), nextBirthDay)), HttpStatus.OK);
    }

}
