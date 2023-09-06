package andrew.backend.app.global.response;

import lombok.*;
import andrew.backend.app.global.exception.ErrorCode;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {
    private int status;
    private String code;
    private String message;

    public CommonResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public CommonResponse(ErrorCode errorCode, String message) {
        this.status = errorCode.getStatus();
        this.code = errorCode.name();
        this.message = message;
    }
}
