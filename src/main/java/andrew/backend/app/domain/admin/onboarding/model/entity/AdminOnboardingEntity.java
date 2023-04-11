package andrew.backend.app.domain.admin.onboarding.model.entity;

import andrew.backend.app.domain.common.file.model.entity.FileEntity;
import andrew.backend.app.domain.main.account.model.entity.CountryCodeEntity;
import andrew.backend.app.global.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class AdminOnboardingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private FileEntity file;

    @ManyToOne(fetch = FetchType.LAZY)
    private CountryCodeEntity countryCode;

    private String title;

    private boolean status;

    private Integer sequence;

}

