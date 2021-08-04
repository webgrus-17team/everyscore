package com.webgrus17.everyscore.config.security;

//import org.springframework.context.annotation.Bean;
import com.webgrus17.everyscore.domain.user.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

// 우선 로그인, 로그아웃 성공시 넘어갈 url 비워두었으므로 실행시 오류 발생할 것
@EnableWebSecurity // WebSecurity 활성화 어노테이션
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }

    // http 관련 인증 설정 하는 메소드
    // 우선 관리자 관련 기능은 여유되면 추가하므로 우선 제외
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/user", "api/v1/join").permitAll() // 누구나 접근 가능한 api
                .anyRequest().hasRole("USER") // 나머지 api는 USER 권한 있어야 함
                .and()                //인증되지 않은 사용자가 접근할 경우, 로그인 페이지로 이동
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/api/v1/user"))
                .and()
                .formLogin()
                .loginPage("/api/v1/login")
                //.successHandler(CustomAuthenticationSuccessHandler) //성공 시의 핸들러
                //.failureHandler(CustomAuthenticationSuccessHandler) //실패 시의 핸들러
                .usernameParameter("id")    //시큐리티의 기본 id값은 username이므로 변경
                .passwordParameter("pw")    //프론트엔드에서 pw로 작업했음
                .defaultSuccessUrl("") // 로그인 성공시 넘어갈 url, 프론트 주소 넣기(로컬이면 포트번호까지)
                .and()
                .logout()
                .logoutSuccessUrl("") // 로그아웃 성공시 넘어갈 url, 프론트 주소 넣기(로컬이면 포트번호까지)
                .invalidateHttpSession(true) // 로그아웃시 저장된 세션 날리기
        ;
    }



    //삭제할 수 있음. 구현되지 않아서 오류가 생기므로 주석처리(import한 것도 주석처리)
    //프론트엔드 json형식의 응답을 보내기 위해 authenticationSuccessHandler 등록
    /*
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return new CustomUrlAuthenticationSuccessHandler();
    }
    */
}