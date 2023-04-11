package andrew.backend.app.domain.main.account.model.entity;

import andrew.backend.app.global.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class RolesUserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rcId;

    @ManyToOne(fetch = FetchType.LAZY)
    private RoleEntity roleEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfoEntity userInfo;
}

