package com.webgrus17.everyscore.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 우선 스프링 시큐리티 사용하지 말지 결정이 안났으므로
// user entity만 기본으로 만들어 둠
@Getter
@NoArgsConstructor
@Entity // 해당 클래스 entity로 지정
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code; // PK

    @Column(unique = true, nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Builder
    public User(String id, String password, String gender, String name, String email) {
        this.id = id;
        this.password = password;
        this.gender = gender;
        this.name = name;
        this.email = email;
    }
}