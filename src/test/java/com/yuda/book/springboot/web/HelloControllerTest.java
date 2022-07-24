package com.yuda.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

/*
 * @RunWith(): 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
 *           여기서는 SpringRunner라는 스프링 실행자를 사용함
 *           = 스프링 부트 테스트와 JUnit 사이의 연결자 역할을 함
 * */
@RunWith(SpringRunner.class)
/*
 * @WebMvcTest(): 스프링 테스트 어노테이션 중 Web spring MVC에 특화된 어노테이션
 *                선언 시 @Controller, @ControllerAdvice 등을 사용할 수 있음(@Service, @Component, @Repository 등은 사용 X)
 * */
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired // 스프링이 관리하는 Bean을 주입받는 어노테이션
    /*
     * 웹 API 테스트 시 사용하는 객체
     * 스프링 MVC 테스트의 시작점
     * 해당 클래스를 통해 HTTP GET, POST 등의 API를 테스트할 수 있음
     * */
    private MockMvc mvc;

    @Test
    public void hello_test() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET을 요청함
                /*
                 * mvc.perform() 메소드의 결과를 중 HTTP Header의 Status를 검증함
                 * 특히 OK(=200)인지 아닌지 검증함
                 * */
                .andExpect(status().isOk())
                /*
                 * mvc.perform() 메소드의 결과 중 응답 본문 내용을 검증함
                 * 여기서는 Controller에서 "hello"를 리턴하기 때문에 해당 값이 "hello"가 맞는지 검증함
                 * */
                .andExpect(content().string(hello));
    }

    @Test
    public void helloTestDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        /*
                        * API 테스트 시 요청 파라미터를 설정
                        * 단, 파라미터는 String만 허용되기 때문에 int -> String 변환 필요
                        * */
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                /*
                 * mvc.perform() 메소드의 결과를 중 HTTP Header의 Status를 검증함
                 * 특히 OK(=200)인지 아닌지 검증함
                 * */
                .andExpect(status().isOk())
                /*
                 * json 응답값을 필드별로 검증할 수 있는 메소드로 $를 기준으로 필드명 명시
                 * */
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
