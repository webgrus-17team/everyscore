package com.webgrus17.everyscore.web;

import com.webgrus17.everyscore.service.user.UserService;
import com.webgrus17.everyscore.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/api/v1/join") // 회원가입 api
    public String signup(UserDto userDto) {
        userService.save(userDto);
        return ""; // 프론트 로그인 사이트로 연결할 것, 그냥 링크 적어도 되는지 찾아보기
    }
}
