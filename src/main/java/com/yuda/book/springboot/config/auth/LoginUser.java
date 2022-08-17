package com.yuda.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 어노테이션이 생성될 수 있는 위치 지정(이 경우 파라미터에만 사용 가능)
@Retention(RetentionPolicy.RUNTIME)
/*
* @interface: 어노테이션 클래스 생성
* */
public @interface LoginUser {
}
