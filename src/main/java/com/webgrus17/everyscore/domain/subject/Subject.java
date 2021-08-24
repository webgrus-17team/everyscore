package com.webgrus17.everyscore.domain.subject;

import com.webgrus17.everyscore.domain.BaseTimeEntity;
import com.webgrus17.everyscore.domain.user.User;
import com.webgrus17.everyscore.domain.user_score.UserScore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//첫글작성에서 post 되고, 메인에서 get

@Entity
@NoArgsConstructor // 생성자 추가
@Getter
public class Subject extends BaseTimeEntity {  //과목 교수명 시험종류 중 하나라도 다르면 다른 튜플 저장해야함
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //PK, AUTO INCREMENT
    private Long id;

    @Column(nullable = false)
    private String Professor_name;

    @Column(nullable = false)
    private String Subject_name;

    // 물론 회의에서 과목명, 교수명만 입력하면 3가지 시험 종류 모두 저장되기로 함
    // 따라서 과목명, 교수명이 생성될 경우, 중간고사, 퀴즈, 기말고사가 동시에 생성되는 것으로 설계하기
    @Column(nullable = false)
    private String Test_type;

    // BaseTimeEntity 상속하기만 하면 생성, 수정 날짜 및 시각이 db에 저장됨
//    @CreatedDate
//    @Column(updatable = false)
//    private LocalDateTime createdDate;
/*
    @OneToMany
    @JoinColumn(name="sub_id") //fk 지정방식
    private List<UserScore> userScoreList;
*/
    @Builder
    public Subject(String Professor_name, String Subject_name, String Test_type){
        this.Professor_name=Professor_name;
        this.Subject_name=Subject_name;
        this.Test_type=Test_type;
    }

}

    /*
    @Column(length=20, nullable = false)
    private String Test_type;
    */