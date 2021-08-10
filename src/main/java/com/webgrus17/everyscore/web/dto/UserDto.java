package com.webgrus17.everyscore.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String pw;
    private String gender;
    private String birthday_year;
    private String birthday_month;
    private String birthday_day;
    private String name;
    private String email;
    private String major;
    private String classnumber;

    // @Builder는 UserService에, 비밀번호 암호화해서 저장해야 하기 때문
}
