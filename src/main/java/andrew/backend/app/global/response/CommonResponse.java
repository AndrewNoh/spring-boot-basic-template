package andrew.backend.app.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Schema(name = "CommonResponse", description = "결과값 응답")
@Data
@ToString
public class CommonResponse {
    @Schema(description = "성공 여부")
    private boolean success;
    @Schema(description = "결과 코드")
    private int code;
    @Schema(description = "응답 메세지")
    private String message;
}
