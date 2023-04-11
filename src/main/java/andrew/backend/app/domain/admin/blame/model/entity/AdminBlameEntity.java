package andrew.backend.app.domain.admin.blame.model.entity;

import andrew.backend.app.domain.admin.blame.model.enums.BlameStatus;
import andrew.backend.app.domain.admin.blame.model.enums.BlameType;
import andrew.backend.app.domain.admin.blame.model.enums.TargetType;
import andrew.backend.app.domain.main.account.model.entity.CountryCodeEntity;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.global.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AdminBlameEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blameId;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserInfoEntity userInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    private CountryCodeEntity countryCode;

    private Long targetId;

    @Enumerated(EnumType.STRING)
    private TargetType targetType;

    @Enumerated(EnumType.STRING)
    private BlameType blameType;

    private String blameBody;

    @Enumerated(EnumType.STRING)
    private BlameStatus status;

    // constructors, getters, setters, and other methods
}
