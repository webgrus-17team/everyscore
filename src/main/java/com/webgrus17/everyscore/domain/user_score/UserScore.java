package com.webgrus17.everyscore.domain.user_score;

import com.webgrus17.everyscore.domain.subject.Subject;
import com.webgrus17.everyscore.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class UserScore {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //PK, AUTO INCREMENT
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)  //user 1명이 user score 여러 개 만들 수 있음 //EAGER방식은 추적 어려움 문제로 변경
    @JoinColumn(name="user_id") //fk 지정방식
    private User user;
    //@Column(nullable=false) //FK 지정해야됨
    //private Long user_id;

    @ManyToOne(fetch=FetchType.LAZY)  //과목 1개에 user socre 여러 개 만들어질 수 있음
    @JoinColumn(name="sub_id") //fk 지정방식
    private Subject subject;
    //@Column(nullable = false)   //FK 지정해야됨
    //private Long sub_id;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Integer difficulty;

    //생성날짜 없어도 됨
    //수정날짜 없어도 됨

    @Builder
    public UserScore(Integer score, Integer difficulty){
        this.score=score;
        this.difficulty=difficulty;
    }
}
