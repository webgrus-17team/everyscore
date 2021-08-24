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

/*
고려해야할 사항
1. get request로 받은 id와 sub_id를 바탕으로 쿼리문을 실행해 테이블을 찾아야한다.
-> 쿼리문 실행 시에 받은 변수명을 어떻게 쿼리에 담을지, 관련 메소드가 있는지 찾아봐야 함
2. 테이블을 집계함수로 처리해 샘플수/평균/중간값/난이도평균/등수를 json에 담아 보내야한다.
3. 프론트엔드에서 차트를 사용하기 때문에 차트출력에 사용할 데이터를 어떻게 넘겨야할지 생각해야한다.
 */