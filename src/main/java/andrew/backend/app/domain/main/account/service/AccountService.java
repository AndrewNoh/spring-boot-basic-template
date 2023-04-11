package andrew.backend.app.domain.main.account.service;



import andrew.backend.app.domain.main.account.model.dto.UserEntityRoleDto;
import andrew.backend.app.domain.main.account.model.dto.join.NormalJoinDto;
import andrew.backend.app.domain.main.account.model.dto.join.SocialJoinDto;
import andrew.backend.app.domain.main.account.model.dto.join.TermsCountryCodeDto;
import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;

import java.util.Optional;

public interface AccountService {
    boolean normalJoin(NormalJoinDto user);

    boolean socialJoin(SocialJoinDto user);

    Optional<UserInfoEntity> findByEmail(String email);

    UserEntityRoleDto getRolesUserEntity(String email);

    TermsCountryCodeDto callDefault(String languageCode);
}
