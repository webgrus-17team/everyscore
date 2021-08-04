package com.webgrus17.everyscore.web;

import com.webgrus17.everyscore.service.user.UserService;
import com.webgrus17.everyscore.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "*") // CORS 설정, 우선 모든 도메인, 요청방식에 대해 허용하고 추후에 프론트 링크 넣어서 제한하기
@RequiredArgsConstructor // 생성자
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/join") // 회원가입 api
    public String signup(UserDto userDto) {
        userService.save(userDto);
        return ""; // 프론트 로그인 사이트로 연결할 것, 그냥 링크 적어도 되는지 찾아보기
    }
}
