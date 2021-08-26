package com.webgrus17.everyscore.web.dto;

import com.webgrus17.everyscore.domain.subject.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectSaveDto {
    private String professorName;
    private String subjectName;
    //private String testType;

    @Builder
    public SubjectSaveDto(final String professorName, final String subjectName){
        this.professorName=professorName;
        this.subjectName=subjectName;
        //this.testType=testType;
    }
    public Subject toEntity1(){
        return Subject.builder()
                .professorName(professorName)
                .subjectName(subjectName)
                .testType("중간고사")
                .build();
    }

    public Subject toEntity2(){
        return Subject.builder()
                .professorName(professorName)
                .subjectName(subjectName)
                .testType("기말고사")
                .build();
    }

    public Subject toEntity3(){
        return Subject.builder()
                .professorName(professorName)
                .subjectName(subjectName)
                .testType("퀴즈")
                .build();
    }
}
