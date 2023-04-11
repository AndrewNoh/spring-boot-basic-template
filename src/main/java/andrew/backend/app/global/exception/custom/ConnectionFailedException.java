package andrew.backend.app.global.exception.custom;

public class ConnectionFailedException extends RuntimeException {

    public ConnectionFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public ConnectionFailedException(String msg) {
        super(msg);
    }

    public ConnectionFailedException() {
        super("접속에 실패하였습니다.");
    }
}
