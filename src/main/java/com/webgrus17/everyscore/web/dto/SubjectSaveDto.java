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
    private String testType;

    @Builder
    public SubjectSaveDto(final String professorName, final String subjectName, final String testType){
        this.professorName=professorName;
        this.subjectName=subjectName;
        this.testType=testType;
    }
    public Subject toEntity(){
        return Subject.builder()
                .professorName(professorName)
                .subjectName(subjectName)
                .testType(testType)
                .build();
    }
}
