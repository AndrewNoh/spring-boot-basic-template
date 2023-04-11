package andrew.backend.app.domain.main.account.model.dto;

import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "LoginRequestDto", description = "로그인 요청 DTO")
@JsonInclude(JsonInclude.Include.NON_NULL) // <--- 추가된 어노테이션
public class LoginRequestDto {
    @Schema(description = "이메일 로그인")
    private LoginEmailDto emailLogin;
    @Schema(description = "소셜 로그인")
    private LoginSocialDto socialLogin;
    @Schema(description = "관리자 로그인(현재 구현 X)")
    private LoginAdminDto adminLogin;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginEmailDto {
        @Schema(description = "로그인 ID(EMAIL)")
        private String email;

        @Schema(description = "로그인 PASSWORD")
        private String password;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginSocialDto {
        @Schema(description = "SNS 종류")
        private SnsProvider provider;

        @Schema(description = "SNS 토큰")
        private String accessToken;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginAdminDto {
        @Schema(description = "어드민 아이디")
        private String adminId;

        @Schema(description = "어드민 비밀번호")
        private String adminPwd;
    }
}