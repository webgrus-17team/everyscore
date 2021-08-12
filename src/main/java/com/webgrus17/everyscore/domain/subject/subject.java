package com.webgrus17.everyscore.domain.subject;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class subject {  //과목, 교수명, 시험종류 중 하나라도 다르면 다른 튜플 저장해야함
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //PK, AUTO INCREMENT
    private Long id;

    @Column(nullable=false)
    private String Subject_name;

    @Column(nullable=false)
    private String Professor_name;

    @Column(length=20, nullable = false)
    private String Test_type;


    //생성날짜
    //수정날짜

    @Builder
    public subject(String Subject_name, String Professor_name, String Test_type, Integer high_score){
        this.Subject_name=Subject_name;
        this.Professor_name= Professor_name;
        this.Test_type=Test_type;
    }
}
