package com.yuda.book.springboot.config.auth;

import com.yuda.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록됨(스프링 시큐리티 설정 활성화)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOauth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 옵션 disable
            .and()
            .authorizeRequests() // URL별 권한 관리를 위한 옵션 시작점, 해당 메소드가 선언되어야 antMatchers 사용 가능
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated() // 값이 설정되지 않은 나머지 URL 설정
            .and()
            .logout() // 로그아웃 설정
                .logoutSuccessUrl("/")
            .and()
            .oauth2Login() // OAuth2 로그인 설정 진입점
                .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때의 설정
                .userService(customOauth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스 구현체 등록
    }
}
