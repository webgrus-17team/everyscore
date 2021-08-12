package com.webgrus17.everyscore.domain.user_score;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class user_score {
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

    //생성날짜
    //수정날짜

    @Builder
    public user_score(Integer score, Integer difficulty){
        this.score=score;
        this.difficulty=difficulty;
    }
}
