package com.example.myblog.controller;

import com.example.myblog.domain.ExampleQuery;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


@RestController
@RequestMapping("/api/simple")
public class SimpleRestController {

    @GetMapping("/no-param")
    public String noParam() {
        //无参
        return "No parameter.";
    }

    @GetMapping(path = "/single-param")
    public String singleParam(String param) {
        //单个可选参数
        return "The parameter is :" + param;
    }

    @GetMapping("/single-param-with-annotation")
    public String singleParamWithAnnotation(@RequestParam("parameter") String param) {
        //单个必传参数
        return "The parameter is :" + param;
    }

    @GetMapping("/single-param-with-default-value")
    public String singleParamWithDefaultValue(@RequestParam(defaultValue = "default") String param) {
        //单个必传参数
        return "The parameter is :" + param;
    }

    @PostMapping("/few-params-with-annotation")
    public String fewParamsWithAnnotation(@RequestParam String paramA, @RequestParam(required = false) Integer paramB) {
        //paramA必传，paramB可选
        return String.format("paramA is :%s,paramB is :%s", paramA, paramB);
    }

    @PostMapping("/large-number-params")
    public String largeNumberParams(@RequestParam String paramA, @RequestParam String paramB, @RequestParam String paramC, @RequestParam String paramD,
                                    @RequestParam String paramE, @RequestParam String paramF, @RequestParam String paramG, @RequestParam String paramH,
                                    @RequestParam String paramI) {
        return String.format("paramA is :%s,paramB is :%s,paramC is :%s,paramD is :%s,paramE is :%s,paramF is :%s," +
                "paramG is :%s,paramH is :%s,paramI is :%s,", paramA, paramB, paramC, paramD, paramE, paramF, paramG, paramH, paramI);
    }

    @PostMapping("/large-number-params-in-body")
    public String largeNumberParams(@RequestBody ExampleQuery query) {
        return String.format("paramA is :%s,paramB is :%s,paramC is :%s,paramD is :%s,paramE is :%s,paramF is :%s," +
                        "paramG is :%s,paramH is :%s,paramI is :%s,", query.getParamA(), query.getParamB(), query.getParamC(),
                query.getParamD(), query.getParamE(), query.getParamF(), query.getParamG(), query.getParamH(), query.getParamI());
    }

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello there.", HttpStatus.OK);
    }

    @GetMapping("/custom-header")
    public ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "customHeader");
        return new ResponseEntity<>("Hello there.", headers, HttpStatus.OK);
    }

    @GetMapping("/next-birth-day/{year}/{month}/{day}")
    public ResponseEntity<Long> nextBirthday(@PathVariable int year,@PathVariable int month,@PathVariable int day) {
        LocalDate birthDate = LocalDate.of(year, month, day);
        if (birthDate.isAfter(LocalDate.now())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        LocalDate nextBirthDay = LocalDate.of(LocalDate.now().getYear(), birthDate.getMonth(), birthDate.getDayOfMonth());
        if (nextBirthDay.isBefore(LocalDate.now())) {
            nextBirthDay = nextBirthDay.plusYears(1);
        }
        return new ResponseEntity<>(DAYS.between(LocalDate.now(), nextBirthDay), HttpStatus.OK);
    }

}
