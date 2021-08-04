package com.webgrus17.everyscore.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String password;
    private String gender;
    private String birth;
    private String name;
    private String email;

    // @Builder는 UserService에, 비밀번호 암호화해서 저장해야 하기 때문
}