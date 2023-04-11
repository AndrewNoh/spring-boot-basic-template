package andrew.backend.app.global.exception.custom;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String msg) {
        super(msg);
    }

    public EntityNotFoundException() {
        super("잘못된 요청입니다.");
    }
}
