package com.webgrus17.everyscore.web;

import com.webgrus17.everyscore.domain.user.User;
import com.webgrus17.everyscore.domain.user.UserRepository;
import com.webgrus17.everyscore.service.user.UserService;
import com.webgrus17.everyscore.web.dto.UserDto;
import com.webgrus17.everyscore.web.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081") // CORS 설정, 해당 주소에서 오는 api요청만 받아드림
@RequiredArgsConstructor // 생성자
@Controller
public class UserController {

    private final UserService userService;

    // 수많은 삽질 끝에 알아냈는데,
    // spring security에서 기본적으로 html form 형식으로 받는 로그인 또한 지원해 줌;;;;
    // 굳이 따로 filter 조작 할필요 없이 WebSecurityConfig에서 설정하면 됨
    // 물론 공부적인 차원에서 직접 구현해보면 좋겠지만, 제공되는 기능을 우선 활용하기로 함.....
    // 로그인 시 구글에서 에러메세지 뜨는건 어쩔 수 없는듯?

    // 회원가입 api
    @PostMapping("/api/v1/join")
    public String signup(UserDto userDto) {
        userService.save(userDto);
        return "redirect:http://localhost:8081/Login_Page.jsp"; // 프론트 로그인 사이트로 연결할 것
    }
}
