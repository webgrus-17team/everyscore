package com.webgrus17.everyscore.domain.user;

import com.webgrus17.everyscore.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor()
@Entity // 해당 클래스 entity로 지정
// BaseTimeEntity 상속하여 생성날짜, 수정날짜 자동 입력
public class User extends BaseTimeEntity implements UserDetails {

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

    // 필수 Override 메소드들 구현

    // 사용자 권한 설정
    // 우선 생성되는 모든 사용자는 USER이므로, USER로 설정
    // 이 메소드는 권한을 무조건 Collection 형태, 그리고 자료형은 GrantedAuthority 사용해야 함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return roles;
    }

    // 사용자의 id를 반환 (unique 해야 함)
    @Override
    public String getUsername() {
        return getId();
    }

    // 사용자의 password를 반환
    @Override
    public String getPassword() {
        return getPassword();
    }

    // 사실 아래 메소드들도 작동하도록 구현해야 하지만,
    // 지금 만들어볼 서비스에서는 사용하지 않을 것이므로 우선 true만 return 시켜줌

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 비밀번호 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }
}