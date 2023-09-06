package andrew.backend.app.global.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import andrew.backend.app.global.exception.ErrorCode;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.name();
        this.message = errorCode.getMessage();
    }
}
