package andrew.backend.app.domain.main.account.model.dto;

import andrew.backend.app.domain.main.account.model.entity.UserInfoEntity;
import andrew.backend.app.domain.main.account.model.enums.Roles;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserEntityRoleDto {
    List<Roles> roles;
    UserInfoEntity userInfo;
}
