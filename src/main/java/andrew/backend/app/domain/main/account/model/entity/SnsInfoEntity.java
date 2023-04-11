package andrew.backend.app.domain.main.account.model.entity;

import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import andrew.backend.app.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SnsInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long snsId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfoEntity userInfo;

    private String snsEmail;

    @Enumerated(EnumType.STRING)
    private SnsProvider provider;

    private String accessId;
}