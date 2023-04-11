package andrew.backend.app.domain.main.account.model.dto.join;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RequestMailDto {
    @Schema(description = "이메일 주소", required = true)
    private String email;
    @Schema(description = "한국이면 True")
    private boolean korea;
}
