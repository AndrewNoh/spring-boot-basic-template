package andrew.backend.app.domain.main.account.model.dto.join;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Schema(name = "UserJoinDto", description = "1eco 공통 회원가입 DTO")
public class UserJoinDto {
    @Schema(description = "이메일주소")
    private String email;

    @Schema(description = "국가 ID")
    private Long code_id;

    @Schema(description = "휴대폰번호")
    private String phone_number;

    @Schema(description = "닉네임")
    private String nickName;

    @Schema(description = "동의 여부 리스트")
    private List<TermsAgreeDto> termsAgreeDtoList;

    @Schema(description = "추천인 코드")
    private String referralCode;
}
