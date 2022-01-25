package com.kiuk.book.springboot.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entitiy 클래스들의 BaseTimeEntity을 상속할 경우 필드들도 칼럼으로 인식하도록 합니다.
@EntityListeners(AuditingEntityListener.class)// BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate; // Entity가 생성되어 저장될 때 시간이 자동 저장됩니다.

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됩니다.
}