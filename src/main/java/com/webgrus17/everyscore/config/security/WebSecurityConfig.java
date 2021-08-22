package com.webgrus17.everyscore.config.security;

import com.webgrus17.everyscore.service.user.UserService;
import com.webgrus17.everyscore.web.CustomUrlAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

// 우선 로그인, 로그아웃 성공시 넘어갈 url 비워두었으므로 실행시 오류 발생할 것
@EnableWebSecurity // WebSecurity 활성화 어노테이션
@RequiredArgsConstructor // final 생성자 추가
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService; // 유저 정보 가져올 서비스

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }

    // http 관련 인증 설정 하는 메소드
    // 우선 관리자 관련 기능은 여유되면 추가하므로 우선 제외
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                //api 테스트를 위해 아래 4줄(.authorizeRquests()에서 .and()까지를 주석처리해주세요
                .authorizeRequests()
                    .antMatchers("/api/v1/user", "/api/v1/join").permitAll() // 누구나 접근 가능한 api
                    .anyRequest().hasRole("USER")
                .and()
                    .exceptionHandling()    //인증되지 않은 사용자 접근 시에 로그인 페이지 이동
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("http://localhost:8081/Login_Page.jsp"))
                .and()
                    .formLogin()
                        .loginPage("http://localhost:8081/Login_Page.jsp")  //로그인페이지를 기본 폼에서 커스텀페이지로 설정
//                        .successHandler(authenticationSuccessHandler()) // 로그인은 json형태가 아니므로 우선 주석처리
                        .usernameParameter("id")    //시큐리티 기본 아이디명이 username이므로 id로 이름 변경
                        .passwordParameter("pw")    //노션 기반으로, password->pw 변경
                        .loginProcessingUrl("/api/v1/user") // 삽질 많이 했는데 결국 security에서 이 기능도 제공해줌
                        .failureForwardUrl("http://localhost:8081/login-fail.jsp") // 로그인 실패시 넘어갈 url(왜인지 작동 안됨)
                        .defaultSuccessUrl("http://localhost:8081/main_3.jsp") // 로그인 성공시 넘어갈 url, 프론트 주소 넣기(로컬이면 포트번호까지)
                .and()
                    .logout()
                        .logoutUrl("/doLogout") // 로그아웃 요청할시 해당 url 입력
                        .logoutSuccessUrl("http://localhost:8081/Login_Page.jsp") // 로그아웃 성공시 넘어갈 url, 프론트 주소 넣기(로컬이면 포트번호까지)
                        .invalidateHttpSession(true) // 로그아웃시 저장된 세션 날리기
        ;
    }

    @Override
    // 로그인 시 필요한 정보
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder()); // 패스워드 인코더 설정
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomUrlAuthenticationSuccessHandler();
    }

}
