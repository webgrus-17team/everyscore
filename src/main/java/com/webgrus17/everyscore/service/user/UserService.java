package com.webgrus17.everyscore.service.user;

import com.webgrus17.everyscore.domain.user.User;
import com.webgrus17.everyscore.domain.user.UserRepository;
import com.webgrus17.everyscore.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // UserDetailService 상속시 필수 Override해야 하는 메소드, 유저 id로 유저 찾기
    @Override
    public User loadUserByUsername(String Id) throws UsernameNotFoundException {
        return userRepository.findById(Id)
                .orElseThrow(() -> new UsernameNotFoundException("id가 존재하지 않습니다: " + Id));
    }

    public Long save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 인코더 선언
        userDto.setPw(encoder.encode(userDto.getPw())); // 암호화

        // db에 저장
        return userRepository.save(User.builder()
            .id(userDto.getId())
            .pw(userDto.getPw())
            .gender(userDto.getGender())
            .birthday(userDto.getBirthday())
            .name(userDto.getName())
            .email(userDto.getEmail())
            .major(userDto.getMajor())
            .classnumber(userDto.getClassnumber()).build()).getCode();
    }

}
