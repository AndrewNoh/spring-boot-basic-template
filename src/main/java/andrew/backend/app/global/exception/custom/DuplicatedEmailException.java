package andrew.backend.app.global.exception.custom;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String msg, Throwable t) {
        super(msg, t);
    }

    public DuplicatedEmailException(String msg) {
        super(msg);
    }

    public DuplicatedEmailException() {
        super("이메일이 중복됩니다.");
    }
 }