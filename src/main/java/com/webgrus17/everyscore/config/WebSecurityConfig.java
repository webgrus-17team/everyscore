package com.webgrus17.everyscore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 작동 여부 미지수, 우선 작성
@EnableWebSecurity // WebSecurity 활성화 어노테이션
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // http 관련 인증 설정 하는 메소드
    // 우선 관리자 관련 기능은 여유되면 추가하므로 우선 제외
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/v1/user", "api/v1/join").permitAll() // 누구나 접근 가능한 api
                    .anyRequest().hasRole("USER") // 나머지 api는 USER 권한 있어야 함
                .and()
                    .formLogin()
                        .defaultSuccessUrl("") // 로그인 성공시 넘어갈 url, 프론트 주소 넣기(로컬이면 포트번호까지)
                .and()
                    .logout()
                        .logoutSuccessUrl("") // 로그아웃 성공시 넘어갈 url, 프론트 주소 넣기(로컬이면 포트번호까지)
                        .invalidateHttpSession(true) // 로그아웃시 저장된 세션 날리기
        ;
    }

}
