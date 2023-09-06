package andrew.backend.app.global.response;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import andrew.backend.app.global.exception.CustomException;
import andrew.backend.app.global.exception.ErrorCode;

import java.util.List;

@Service
public class ResponseService {
    public <T> SingleResponse<T> getSingleResponse(T data) {
        SingleResponse<T> singleResponse = new SingleResponse<>(data);
        if (data == null || data == "") setNoContentResponse(singleResponse);
        else setSuccessResponse(singleResponse);
        return singleResponse;
    }

    public <T> ListResponse<T> getListResponse(List<T> dataList) {
        ListResponse<T> listResponse = new ListResponse<>(dataList);
        if (dataList == null || dataList.isEmpty()) setNoContentResponse(listResponse);
        else setSuccessResponse(listResponse);
        return listResponse;
    }

    public <T> PageResponse<T> getPageResponse(Page<T> dataPage) {
        PageResponse<T> pageResponse = new PageResponse<>(dataPage);
        if (dataPage == null || dataPage.isEmpty()) setNoContentResponse(pageResponse);
        else setSuccessResponse(pageResponse);
        return pageResponse;
    }

    void setSuccessResponse(CommonResponse response) {
        response.setCode(ErrorCode.SUCCESS.name());
        response.setStatus(ErrorCode.SUCCESS.getStatus());
        response.setMessage(ErrorCode.SUCCESS.getMessage());
    }

    void setNoContentResponse(CommonResponse response) {
        response.setCode(ErrorCode.NO_CONTENT.name());
        response.setStatus(ErrorCode.NO_CONTENT.getStatus());
        response.setMessage(ErrorCode.NO_CONTENT.getMessage());
    }


    public CommonResponse defaultSuccessResponse() {
        CommonResponse response = new CommonResponse();
        setSuccessResponse(response);
        return response;
    }

    public ResponseEntity<CommonResponse> error(CustomException e, String text) {
        if (text == null) text = e.getErrorCode().getMessage();

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(CommonResponse.builder()
                        .status(e.getErrorCode().getStatus())
                        .code(e.getErrorCode().name())
                        .message(text)
                        .build());
    }




}