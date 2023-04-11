package andrew.backend.app.global.exception.custom;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidTokenException(String msg) {
        super(msg);
    }

    public InvalidTokenException() {
        super("토큰이 존재하지 않습니다.");
    }
}