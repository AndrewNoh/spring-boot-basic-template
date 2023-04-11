package andrew.backend.app.global.exception.custom;

public class CountryCodeNotFoundException extends RuntimeException{
    public CountryCodeNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public CountryCodeNotFoundException(String msg) {
        super(msg);
    }

    public CountryCodeNotFoundException() {
        super("해당 국가코드를 찾을 수 없습니다.");
    }
}
