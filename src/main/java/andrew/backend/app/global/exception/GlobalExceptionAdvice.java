package andrew.backend.app.global.exception;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import andrew.backend.app.global.response.CommonResponse;
import andrew.backend.app.global.response.ResponseService;

import java.util.NoSuchElementException;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionAdvice {

    private final ResponseService resp;

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<CommonResponse> handleCustomException(CustomException e) {
        e.printStackTrace();
        log.error("[handleCustomException] {} : {}", e.getErrorCode().name(), e.getErrorCode().getMessage());
        return resp.error(e, e.getText());
    }


    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<CommonResponse> handleNoSuchElementException(NoSuchElementException e) {
        log.error("[handleNoSuchElementException] Element not found: {}", e.getMessage());
        return ResponseEntity.status(ErrorCode.ENTITY_NOT_FOUND.getStatus())
                .body(CommonResponse.builder()
                        .status(ErrorCode.ENTITY_NOT_FOUND.getStatus())
                        .code(ErrorCode.ENTITY_NOT_FOUND.name())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity<CommonResponse> handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        log.error("[handleInvalidDataAccessApiUsageException] Element not found: {}", e.getMessage());
        return ResponseEntity.status(ErrorCode.NOT_VALID_METHOD_ARGUMENT.getStatus())
                .body(CommonResponse.builder()
                        .status(ErrorCode.NOT_VALID_METHOD_ARGUMENT.getStatus())
                        .code(ErrorCode.NOT_VALID_METHOD_ARGUMENT.name())
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[handleMethodArgumentNotValidException] Element not found: {}", e.getMessage());
        return ResponseEntity.status(ErrorCode.NOT_VALID_METHOD_ARGUMENT.getStatus())
                .body(CommonResponse.builder()
                        .status(ErrorCode.NOT_VALID_METHOD_ARGUMENT.getStatus())
                        .code(ErrorCode.NOT_VALID_METHOD_ARGUMENT.name())
                        .message(e.getMessage())
                        .build());
    }


}
