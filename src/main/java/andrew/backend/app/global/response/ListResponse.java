package andrew.backend.app.global.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class ListResponse<T> extends CommonResponse {
    private List<T> dataList;
}
