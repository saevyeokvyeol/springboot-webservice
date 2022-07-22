package com.yuda.book.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController: @Controller + @ResponseBody
 *                  JSON을 반환하는 Ajax 전용 컨트롤러 선언 시 사용
 * */
@RestController
public class HelloController {

    /**
     * HTTP method 중 GET 요청을 받을 수 있는 API 선언 시 사용
    * */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
