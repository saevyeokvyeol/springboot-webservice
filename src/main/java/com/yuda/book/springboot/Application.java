package com.yuda.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
 * @SpringBootApplication: 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 자동으로 설정하는 어노테이션
 *                         해당 어노테이션이 있는 위치부터 설정을 읽기 때문에 해당 어노테이션을 사용한 클래스는 프로젝트 최상단에 위치해야 함
 * */
@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // SpringApplicationApplication.run(): 내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }
}
