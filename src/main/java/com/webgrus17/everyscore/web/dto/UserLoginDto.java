package com.webgrus17.everyscore.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    private String id;
    private String password;

    // 생성자를 사용할지, @Setter를 사용할지는 미정, 추후에 고치기
    // 우선 @Setter 작성해 놈
}
