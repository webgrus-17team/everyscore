package com.webgrus17.everyscore.domain.subject;

import com.webgrus17.everyscore.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

//첫글작성에서 post 되고, 메인에서 get

@Entity
@Getter
public class Subject {  //과목 교수명 시험종류 중 하나라도 다르면 다른 튜플 저장해야함
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)  //PK, AUTO INCREMENT
    private Long id;

    @Column(nullable = false)
    private String Professor_name;

    @Column(nullable = false)
    private String Subject_name;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Builder
    public Subject(String Professor_name, String Subject_name){
        this.Professor_name=Professor_name;
        this.Subject_name=Subject_name;
    }

}

    /*
    @Column(length=20, nullable = false)
    private String Test_type;
    */