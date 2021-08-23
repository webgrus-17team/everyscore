package com.webgrus17.everyscore.domain.user_score;

import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public class UserScoreVO {
    private Integer sampleSize;
    private Double average;
    private Double medianValue;
    private Double averageOfLevel;
    private Integer rank;

    //위치를 여기에 할지, controller에서 request받은 직후에 할지 고민
    //쿼리 내부 id_par과 sub_id_par는 프론트에서 받은 값으로, 아래와 같이 쓸 때 그 변수명이 실행될지 의문
    @Query("SELECT x FROM UserScore x WHERE x.id=id_par, x.sub_id=sub_id_par")
    UserScore find(String id_par, String sub_id_par){

        //Collection<UserScore>
    }
}
