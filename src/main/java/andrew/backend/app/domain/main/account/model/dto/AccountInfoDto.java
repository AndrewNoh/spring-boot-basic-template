package andrew.backend.app.domain.main.account.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UserInfoDto", description = "회원정보 DTO")
public class AccountInfoDto {

    @Schema(description = "값 부여 x 자동생성")
    private long id;

    @Schema(description = "회원이름")
    private String userName;

    @Schema(description = "이메일주소")
    private String email;

    @Schema(description = "비밀번호")
    private String password;

    @Schema(description = "핸드폰번호")
    private String phone;

    @Schema(description = "유저 권한", defaultValue = "ROLE_USER", allowableValues = {"ROLE_USER", "ROLE_ADMIN"})
    private String roles;

    @Schema(description = "값 부여 x 자동생성")
    private LocalDateTime created_at;

    @Schema(description = "값 부여 x 자동생성")
    private LocalDateTime updated_at;
}

