package andrew.backend.app.global.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //== 200 ==//
    SUCCESS(HttpStatus.OK.value(), "OK"),

    //== 204 ==//
    NO_CONTENT(HttpStatus.NO_CONTENT.value(), "No Content"),

    //== 400 ==//
    NOT_VALID_METHOD_ARGUMENT(HttpStatus.BAD_REQUEST.value(), "유효하지 않은 Request Body 혹은 Argument입니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "해당 사용자를 찾을 수 없습니다."),
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "해당 정보을 찾을 수 없습니다."),
    CODE_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "등록되어 있지 않은 코드 입니다."),
    NOT_EMAIL_USER(HttpStatus.BAD_REQUEST.value(), "이메일 유저가 아닙니다."),

    //== 401 ==//
    INVALID_PHONE_NUMBER(HttpStatus.UNAUTHORIZED.value(), "Invalid phone number."),
    INVALID_FILE_EXTENSION(HttpStatus.UNAUTHORIZED.value(), "Invalid file extension."),
    NOT_MATCH_AUTHENTICATION(HttpStatus.UNAUTHORIZED.value(), "The authentication number does not match."),

    //== 403 ==//
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "Access denied"),

    //== 404 ==//
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "File Not Found"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found"),
    NOT_FOUND_EMAIL(HttpStatus.NOT_FOUND.value(), "이메일 주소를 찾을 수 업습니다."),
    WITHDRAWN_USER(HttpStatus.BAD_REQUEST.value(), "탈퇴한 회원입니다."),
    KEY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "인증번호가 만료 되었습니다."),


    //== 405 ==//
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed"),
    DELETION_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "현재 삭제할 수 없는 상태 입니다."),
    STATUS_CHANGE_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "상태값이 변경이 완료되어 더 이상 변경이 불가합니다."),


    //== 409 ==//
    CREATE_FAILED(HttpStatus.CONFLICT.value(), "작성 실패"),
    UPDATE_FAILED(HttpStatus.CONFLICT.value(), "업데이트 실패"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT.value(), "중복된 이메일"),
    DUPLICATE_ID(HttpStatus.CONFLICT.value(), "중복된 아이디"),
    DUPLICATE_PHONE(HttpStatus.CONFLICT.value(), "중복된 휴대폰번호"),

    //== 413 ==//
    FILE_TOO_LARGE(HttpStatus.PAYLOAD_TOO_LARGE.value(), "File size is too large."),

    //== 422 ==//
    ALREADY_SUCCESS(HttpStatus.UNPROCESSABLE_ENTITY.value(), "이미 전송완료되어 수정이 불가합니다."),

    //== 500 ==//
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"),
    SNS_LINK_FAILURE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to link with SNS."),
    TWILIO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Twilio Error"),
    FIRE_BASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Fire base error"),
    JAVAX_MAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Mail error"),
    AWS_S3_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "File upload fail"),
    GOOGLE_GEOCODE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "File upload fail"),

    //== 503 ==//
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), "Service Unavailable"),

    //== security error ==//
    INVALID_JWT_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Invalid access jwt token."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST.value(), "Passwords do not match."),
    INVALID_CREDENTIALS(HttpStatus.BAD_REQUEST.value(), "Invalid ID or password."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "Unauthorized"),
    ACCOUNT_LOCKED(HttpStatus.LOCKED.value(), "계정 잠김"),
    ACCOUNT_SUSPENDED(HttpStatus.NOT_ACCEPTABLE.value(), "계정 정지"),
    ACCOUNT_WITHDRAWN(HttpStatus.NOT_FOUND.value(), "계정 탈퇴");

    private final int status;
    private final String message;
}
