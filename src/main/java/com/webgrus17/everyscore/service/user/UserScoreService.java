package com.webgrus17.everyscore.service.user;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.user_score.UserScore;
import com.webgrus17.everyscore.domain.user_score.UserScoreRepository;
import com.webgrus17.everyscore.web.dto.UserScoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserScoreService {
    private final UserScoreRepository userScoreRepository;

    @Transactional
    public Long save(final UserScoreDto userScoreDto){
        return userScoreRepository.save(userScoreDto.toEntity()).getId();
    }

    @Transactional
    public List<UserScore> findBySubject(Subject subject) {
        return userScoreRepository.findBySubject(subject);
    }
}
