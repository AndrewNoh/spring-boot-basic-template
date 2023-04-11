package andrew.backend.app.global.exception;


import andrew.backend.app.global.exception.custom.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import andrew.backend.app.global.exception.custom.*;
import andrew.backend.app.global.response.CommonResponse;
import andrew.backend.app.global.response.ResponseService;

import javax.naming.SizeLimitExceededException;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionAdvice {

    private final ResponseService resp;

    @ExceptionHandler(NotFoundEmailException.class)
    protected CommonResponse notFoundEmail(NotFoundEmailException e) {
        e.printStackTrace();
        return resp.setFailResponse(3005, e.getMessage());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    protected CommonResponse internalAuthenticationService(InternalAuthenticationServiceException e) {
        e.printStackTrace();
        return resp.setFailResponse(3301, e.getMessage());
    }

    @ExceptionHandler(ConnectionFailedException.class)
    protected CommonResponse connectionFailedException(ConnectionFailedException e) {
        e.printStackTrace();
        return resp.setFailResponse(3009, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected CommonResponse illegalArgumentExceptionService(IllegalArgumentException e) {
        e.printStackTrace();
        return resp.setFailResponse(3004, e.getMessage());
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    protected CommonResponse sizeLimit(SizeLimitExceededException e) {
        e.printStackTrace();
        return resp.setFailResponse(3402, "파일 용량이 너무 큽니다.");
    }

    @ExceptionHandler(FileUploadException.class)
    protected CommonResponse cFailUpload(FileUploadException e) {
        e.printStackTrace();
        return resp.setFailResponse(3403, e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected CommonResponse entityNotFoundException(EntityNotFoundException e) {
        e.printStackTrace();
        return resp.setFailResponse(3005, e.getMessage());
    }

    @ExceptionHandler(InvalidTokenException.class)
    protected CommonResponse invalidTokenException(InvalidTokenException e) {
        e.printStackTrace();
        return resp.setFailResponse(3004, e.getMessage());
    }
    @ExceptionHandler(DuplicatedEmailException.class)
    protected CommonResponse duplicatedEmailException(DuplicatedEmailException e) {
        e.printStackTrace();
        return resp.setFailResponse(3004, e.getMessage());
    }



}
