package andrew.backend.app.domain.main.account.model.entity;

import andrew.backend.app.domain.common.file.model.entity.FileEntity;
import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import andrew.backend.app.global.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private LanguageEntity language;

    @ManyToOne(fetch = FetchType.LAZY)
    private AddressEntity address;

    @ManyToOne(fetch = FetchType.LAZY)
    private CountryCodeEntity countryCode;

    private String email;

    private String password;

    private String nickname;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private SnsProvider joinType;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<RolesUserEntity> rolesUserEntityList;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<SnsInfoEntity> snsInfoList;

    @OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
    private List<UserTermEntity> userTermEntityList;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserCheckEntity userCheck;

    @OneToOne(fetch = FetchType.LAZY)
    private FileEntity profileImg;

}


