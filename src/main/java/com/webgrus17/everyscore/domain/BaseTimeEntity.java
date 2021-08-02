package com.webgrus17.everyscore.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// JPA Auditing 활용하여 생성일, 수정일 자동 저장
// entity 클래스에서 해당 클래스를 상속하기만 하면 됨

@Getter
@MappedSuperclass // JPA Entity 클래스가 해당 클래스를 상속할 경우, 필드들(createdDate, modifiedDate)도 칼럼으로 인식함
@EntityListeners(AuditingEntityListener.class) // JPA Auditing 추가, 자동으로 시간 넣어줌
public class BaseTimeEntity {

    // 실제로 저장되는 형식 변경
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreatedDate
    private LocalDateTime createdDate; // 생성되어 저장된 시간

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @LastModifiedDate
    private LocalDateTime modifiedDate; // 마지막으로 수정한 시간
}
