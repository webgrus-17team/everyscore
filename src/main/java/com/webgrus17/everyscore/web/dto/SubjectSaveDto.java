package com.webgrus17.everyscore.web.dto;

import com.webgrus17.everyscore.domain.subject.Subject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubjectSaveDto {
    private String Professor_name;
    private String Subject_name;
    private String Test_type;

    @Builder
    public SubjectSaveDto(final String Professor_name, final String Subject_name, final String Test_type){
        this.Professor_name=Professor_name;
        this.Subject_name=Subject_name;
        this.Test_type=Test_type;
    }
    public Subject toEntity(){
        return Subject.builder()
                .Professor_name(Professor_name)
                .Subject_name(Subject_name)
                .Test_type(Test_type)
                .build();
    }
}
