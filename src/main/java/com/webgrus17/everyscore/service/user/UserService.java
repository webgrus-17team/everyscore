package com.webgrus17.everyscore.service.user;

import com.webgrus17.everyscore.domain.user.User;
import com.webgrus17.everyscore.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    // UserDetailService 상속시 필수 Override해야 하는 메소드, 유저 id로 유저 찾기
    @Override
    public User loadUserByUsername(String Id) throws UsernameNotFoundException {
        return userRepository.findById(Id)
                .orElseThrow(() -> new UsernameNotFoundException(Id));
    }

}
