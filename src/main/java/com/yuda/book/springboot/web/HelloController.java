package com.yuda.book.springboot.web;

import com.yuda.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
 * @RestController: @Controller + @ResponseBody
 *                  JSON을 반환하는 Ajax 전용 컨트롤러 선언 시 사용
 * */
@RestController
public class HelloController {

    /*
    * @GetMapping: HTTP method 중 GET 요청을 받을 수 있는 API 선언 시 사용
    * */
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    /*
     * @RequestParam(): 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
     *                  (@RequestParam("변수명") 자료형 변수명) 형식으로 작성
     * */
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
