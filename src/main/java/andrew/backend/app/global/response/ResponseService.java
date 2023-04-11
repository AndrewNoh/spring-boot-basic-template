package andrew.backend.app.global.response;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {
    public <T> SingleResponse<T> getSingleResponse(T data) {
        SingleResponse<T> singleResponse = new SingleResponse<>();
        singleResponse.setData(data);
        setSuccessResponse(singleResponse);
        return singleResponse;
    }

    public <T> ListResponse<T> getListResponse(List<T> dataList) {
        ListResponse<T> listResponse = new ListResponse<>();
        listResponse.setDataList(dataList);
        setSuccessResponse(listResponse);
        return listResponse;
    }

    void setSuccessResponse(CommonResponse response) {
        response.setCode(200);
        response.setSuccess(true);
        response.setMessage("SUCCESS");
    }

    public CommonResponse setSuccessResponse(int code, String message) {
        CommonResponse response = new CommonResponse();
        response.setCode(code);
        response.setSuccess(true);
        response.setMessage(message);
        return response;
    }

    public CommonResponse defaultSuccessResponse() {
        CommonResponse response = new CommonResponse();
        setSuccessResponse(response);
        return response;
    }

    public CommonResponse setFailResponse(int code, String message) {
        CommonResponse response = new CommonResponse();
        response.setCode(code);
        response.setSuccess(false);
        response.setMessage(message);
        return response;
    }
}