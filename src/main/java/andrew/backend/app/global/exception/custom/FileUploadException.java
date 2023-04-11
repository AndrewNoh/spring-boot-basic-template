package andrew.backend.app.global.exception.custom;

public class FileUploadException extends RuntimeException{
    public FileUploadException(String msg, Throwable t) {
        super(msg, t);
    }

    public FileUploadException(String msg) {
        super(msg);
    }

    public FileUploadException() {
        super("파일 업로드에 실패하였습니다.");
    }
}
