package andrew.backend.app.domain.main.account.model.dto.join;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "termsAgreeDto", description = "유저 약관 동의 DTO")
public class TermsAgreeDto {

    @Schema(description = "약관 아이디")
    private long termsId;

    @Schema(description = "동의 여부")
    private boolean agree;

}
