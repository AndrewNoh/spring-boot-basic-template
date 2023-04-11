package andrew.backend.app.global.exception.custom;

public class NotFoundEmailException extends RuntimeException {

    public NotFoundEmailException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundEmailException(String msg) {
        super(msg);
    }

    public NotFoundEmailException() {
        super("존재하지 않는 아이디입니다.");
    }
}
