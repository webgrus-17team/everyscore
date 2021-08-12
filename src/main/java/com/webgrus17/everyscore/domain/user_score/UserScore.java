package com.webgrus17.everyscore.domain.user_score;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserScore {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //PK, AUTO INCREMENT
    private Long id;

    @Column(nullable=false) //FK 지정해야됨
    private Long user_id;

    @Column(nullable = false)   //FK 지정해야됨
    private Long sub_id;

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
