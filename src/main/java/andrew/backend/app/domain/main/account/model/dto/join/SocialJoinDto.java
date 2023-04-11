package andrew.backend.app.domain.main.account.model.dto.join;

import andrew.backend.app.domain.main.account.model.enums.SnsProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "SocialJoinDto", description = "소셜 회원가입 DTO")
public class SocialJoinDto extends UserJoinDto {
    @Schema(description = "SNS 종류")
    private SnsProvider provider;

    @Schema(description = "SNS 토큰")
    private String accessToken;

}
