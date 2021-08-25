package com.webgrus17.everyscore.domain.user_score;

import com.webgrus17.everyscore.domain.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserScoreRepository extends JpaRepository<UserScore, Long> {

    List<UserScore> findBySubject(Subject subject);
}
