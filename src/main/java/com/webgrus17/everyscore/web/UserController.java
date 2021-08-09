package com.webgrus17.everyscore.web;

import com.webgrus17.everyscore.service.user.UserService;
import com.webgrus17.everyscore.web.dto.UserDto;
import com.webgrus17.everyscore.web.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "*") // CORS 설정, 우선 모든 도메인, 요청방식에 대해 허용하고 추후에 프론트 링크 넣어서 제한하기
@RequiredArgsConstructor // 생성자
@Controller
public class UserController {

    private final UserService userService;

    // 로그인 api
    @PostMapping("/api/v1/user")
    public String login(UserLoginDto loginDto) {

        /*
        개발할 예정
        기본적으로 spring security에서 제공하는 login이 아닌, 직접 구현해야 함

        우선 대략적인 설계

        1. 아이디로 유저 찾는 서비스
        2. DB로부터 유저 정보 받아 AuthenticationProvider에게 전달해줄 객체
        3. 2번의 객체 받아서 비밀번호 검증하기, 틀리면 예외 던짐, 맞으면 인증과 권한을 담기
        4. 2번의 객체를 WebSecurityConfig에 빈으로 주입, authenticationProvider()메소드의 파라미터로 등록

        이대로 될지는 모르겠지만 이런 흐름으로 제작하면 좋을듯
        */

        return "redirect:http://localhost:8081/main_3.jsp";
    }

    // 회원가입 api
    @PostMapping("/api/v1/join")
    public String signup(UserDto userDto) {
        userService.save(userDto);
        return "redirect:http://localhost:8081/Login_Page.jsp"; // 프론트 로그인 사이트로 연결할 것
    }
}
