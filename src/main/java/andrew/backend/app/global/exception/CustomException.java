package andrew.backend.app.global.exception;

import lombok.Getter;
@Getter
public class CustomException extends RuntimeException{

    private final ErrorCode errorCode;
    private String text;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, String text) {
        this.errorCode = errorCode;
        this.text = text;
    }
}
