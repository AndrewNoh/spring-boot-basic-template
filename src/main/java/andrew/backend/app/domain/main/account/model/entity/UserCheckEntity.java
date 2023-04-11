package andrew.backend.app.domain.main.account.model.entity;

import andrew.backend.app.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserCheckEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private UserInfoEntity user;

    private Integer retryCount = 0;

    public void incrementRetryCount() {
        this.retryCount++;
    }
}
