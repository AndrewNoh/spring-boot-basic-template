package andrew.backend.app.global.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)// Auditing(자동으로 값 매핑) 기능 추가
@Getter
@MappedSuperclass// BaseEntity를 상속한 엔티티들은 아래 필드들을 컬럼으로 인식하게 된다.
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

}
