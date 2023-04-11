package andrew.backend.app.domain.main.account.model.dto.join;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "NormalJoinDto", description = "회원가입 DTO")
public class NormalJoinDto extends UserJoinDto {
    @Schema(description = "비밀번호")
    private String password;
}
