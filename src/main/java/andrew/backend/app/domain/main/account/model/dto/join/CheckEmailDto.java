package andrew.backend.app.domain.main.account.model.dto.join;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CheckEmailDto {
    @Schema(description = "이메일 중복체크")
    private String email;
}
