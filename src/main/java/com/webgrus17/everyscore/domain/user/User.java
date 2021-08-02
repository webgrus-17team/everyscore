package com.webgrus17.everyscore.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor()
@Entity // 해당 클래스 entity로 지정
public class User {

    @Id
    @GeneratedValue // pk
    @Column(length = 20) // 최대 길이 제한 20
    private String id; // 유저 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(length = 10, nullable = false)
    private String gender; // 성별

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Builder
    public User(String id, String password, String gender, String birth, String name, String email) {
        this.id = id;
        this.password = password;
        this.gender = gender;
        this.birth = birth;
        this.name = name;
        this.email = email;
    }
}