package com.webgrus17.everyscore.web.dto;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.user.User;
import com.webgrus17.everyscore.domain.user_score.UserScore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserScoreDto {
    private User user;
    private Subject subject;
    private Integer myscore;
    private Integer level;

    @Builder
    public UserScoreDto(final User user, final Subject subject, final Integer myscore, final Integer level){
        this.user=user;
        this.subject=subject;
        this.myscore=myscore;
        this.level=level;
    }

    public UserScore toEntity(){
        return UserScore.builder()
                .user(user)
                .subject(subject)
                .myscore(myscore)
                .level(level)
                .build();
    }
}
